package com.example.fridge_partner.fragment;

import android.app.Activity;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.fridge_partner.GridSpacingItemDecoration;
import com.example.fridge_partner.R;
import com.example.fridge_partner.ThirdMainActivity;
import com.example.fridge_partner.adapter.FridgeAdapter;
import com.example.fridge_partner.entity.FridgeEntity;
import com.example.fridge_partner.model.FridgeModelManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements FridgeAdapter.OnFridgeClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private RecyclerView mRecyclerView;
    private FridgeAdapter mAdapter;
    private List<FridgeEntity> mFridgeList;

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
        mFridgeList.addAll(FridgeModelManager.getModels());

        mRecyclerView = view.findViewById(R.id.fridgeRecyclerView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        mAdapter = new FridgeAdapter(mFridgeList, this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(2, 30, true)); // Adjust spacing as necessary


        AddButton.setOnClickListener(v -> showDialog());
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

        builder.setPositiveButton("Create", (dialog, which) -> {
            String num1 = editTextForzen.getText().toString();
            String num2 = editTextFreezing.getText().toString();
            String name = editTextName.getText().toString();
            String desc = editTextDescription.getText().toString();
            addFridge(name, desc, num1, num2);
        });
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
            if (currentValue < 3) {
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


    public void addFridge(String fridge, String description, String num1, String num2) {
        int num11 = Integer.parseInt(num1);
        int num22 = Integer.parseInt(num2);
        FridgeEntity entity = new FridgeEntity(fridge, description, num11, num22);
        List<FridgeEntity> fridgeEntities = FridgeModelManager.addFridge(entity);
        mFridgeList.clear();
        mFridgeList.addAll(fridgeEntities);
        mAdapter.notifyItemInserted(mFridgeList.size() - 1);
    }


    @Override
    public void onFridgeClick(FridgeEntity entity) {
        ThirdMainActivity.startThirdMainActivity(this.getContext(),entity);
    }

    @Override
    public void onFridgeLongClick(FridgeEntity entity) {
        Activity activity = getActivity();
        if (activity != null) {
            AlertDialog longClickDialog = new AlertDialog.Builder(activity)
                    .setTitle("Tips")
                    .setMessage("Do you want delete " + entity.getTitle())
                    .setNegativeButton("cancel", (dialog, which) -> {

                    })
                    .setPositiveButton("ok", (dialog, which) -> {
                        FridgeModelManager.deleteFridge(entity);
                        int index = mFridgeList.indexOf(entity);
                        mFridgeList.remove(entity);
                        mAdapter.notifyItemRemoved(index);
                    }).show();
            longClickDialog.getButton(AlertDialog.BUTTON_POSITIVE)
                    .setTextColor(ContextCompat.getColor(activity, R.color.teal_200));
        }

    }
}