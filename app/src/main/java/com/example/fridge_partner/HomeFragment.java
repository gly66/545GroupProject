package com.example.fridge_partner;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private RecyclerView mRecyclerView;
    private FridgeAdapter mAdapter;
    private ArrayList<FridgeAdapter.Item> mFridgeList;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        FloatingActionButton AddButton = view.findViewById(R.id.floatingActionButton);


        mFridgeList = new ArrayList<>();
        mFridgeList.add(new FridgeAdapter.Item("Fridge 1", "Description", R.drawable.fridge));

        mRecyclerView = view.findViewById(R.id.fridgeRecyclerView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        mAdapter = new FridgeAdapter(mFridgeList, fridge -> {
            Intent intent = new Intent(getActivity(), ThirdMainActivity.class);
            intent.putExtra("fridgeName", fridge);
            startActivity(intent);
        });
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(2, 50, true)); // Adjust spacing as necessary



        AddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
        return view;
    }


    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.create_fridge, null);
        builder.setView(dialogView);

        final EditText editTextName = dialogView.findViewById(R.id.fridgename);
        final EditText editTextDescription = dialogView.findViewById(R.id.description);
        final EditText editTextForzen = dialogView.findViewById(R.id.editTextForzen);
        final EditText editTextFreezing = dialogView.findViewById(R.id.editTextFreezing);
        Button buttonIncreaseForzen = dialogView.findViewById(R.id.buttonIncreaseForzen);
        Button buttonDecreaseForzen = dialogView.findViewById(R.id.buttonDecreaseForzen);
        Button buttonIncreaseFreezing = dialogView.findViewById(R.id.buttonIncreaseFreezing);
        Button buttonDecreaseFreezing = dialogView.findViewById(R.id.buttonDecreaseFreezing);
//        Button btn_create = dialogView.findViewById(R.id.createButton);
//        Button btn_reset = dialogView.findViewById(R.id.resetButton);

        setupIncrementDecrementListeners(buttonIncreaseForzen, buttonDecreaseForzen, editTextForzen);
        setupIncrementDecrementListeners(buttonIncreaseFreezing, buttonDecreaseFreezing, editTextFreezing);
        builder.setPositiveButton("Create", (dialog, which) ->addFridge(editTextName.getText().toString(),editTextDescription.getText().toString()));
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

//        btn_create.setOnClickListener(v -> {
//             Implement create functionality
//             This could involve collecting all input data and sending it to a server or local database
//            addFridge(editTextName.getText().toString(),editTextDescription.getText().toString());
//            dialog.dismiss();
//        });
//
//        btn_reset.setOnClickListener(v -> {
//            resetFields();
//        });

        builder.show();
    }

    private void setupIncrementDecrementListeners(Button increase, Button decrease, EditText editText) {
        increase.setOnClickListener(v -> {
            int currentValue = Integer.parseInt(editText.getText().toString());
            if (currentValue < 3){
                editText.setText(String.valueOf(currentValue + 1));
            }
        });
        decrease.setOnClickListener(v -> {
            int currentValue = Integer.parseInt(editText.getText().toString());
            if (currentValue > 0) {
                editText.setText(String.valueOf(currentValue - 1));
            }
        });
    }

//    private void resetFields() {
//        editTextName.setText("");
//        editTextDescription.setText("");
//        editTextForzen.setText("0");
//        editTextFreezing.setText("0");
//    }

    public void addFridge(String fridge, String description) {

        mFridgeList.add(new FridgeAdapter.Item(fridge, description, R.drawable.fridge));
        mAdapter.notifyItemInserted(mFridgeList.size() - 1);

    }


}