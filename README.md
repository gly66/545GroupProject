<<<<<<< HEAD
# 545GroupProject
Fridge Assistant  

Wendy Wen, Jeremy Gao  

Version #2.0  

## Summary of Project  
Your kitchen companion! Say goodbye to food waste and fridge rummaging. With 40% of food waste occurring at home (EPA), our app empowers users to reduce waste by tracking fridge contents and expiry dates with precision. Discover recipe ideas based on available ingredients, linked to Chrome for detailed cooking instructions. Plus, streamline household management with multi-device and multi-account support. Join us in revolutionizing kitchen efficiency and minimizing food waste with Fridge Assistant today!  

## Project Analysis  
### Value Proposition  
In America, food waste is a significant issue, with about 40% of food produced going uneaten (EPA). Americans throw away 103 pounds of spoiled food from their fridge every year[1]. This waste occurs at all stages—from farms and retail to households—costing billions and harming the environment through wasted resources and increased greenhouse gas emissions from decomposing organic matter. Our Fridge Assistant aims to improve this waste through effective fridge management.  

### Primary Purpose  
Our applications are designed to help users meticulously track the contents of their refrigerators and the shelf life of each item, enabling them to minimize waste and reduce living costs. By providing tools to monitor food inventory accurately, our apps empower individuals to make informed decisions about food usage before it spoils. This not only helps households save money but also contributes to broader societal benefits. Reducing food waste is crucial for maximizing resource efficiency and minimizing the environmental impact, particularly in terms of decreasing greenhouse gas emissions that result from the decomposition of wasted food in landfills. These efforts collectively foster a more sustainable planet.  

### Target Audience  
**Families with Specific Characteristics:**  
Large Families: Households with multiple children or extended family members living together tend to have higher food consumption and waste. Targeting these families could address significant food management challenges.  
Suburban Families: These families often have more storage space, leading to overbuying and increased food spoilage. Tailoring features to help them manage bulk purchases effectively could reduce waste.  
Environmentally Conscious Families: Families that are already motivated by sustainability efforts are more likely to adopt tools that help reduce their environmental footprint, including minimizing food waste.  
**Co-renters with Defined Needs:**    
Student Sharers: Groups of students living together often face challenges in coordinating food purchases and consumption, leading to unnecessary waste. Your app can help manage shared responsibilities and improve communication regarding food usage.  
Young Professionals in Shared Housing: Like students, young working adults sharing accommodation may struggle with similar issues around food management. Offering features that facilitate split expenses and joint shopping lists can be particularly appealing.  
Urban Co-renters: Those living in urban environments tend to shop more frequently but in smaller quantities due to limited storage space. Features that help track perishable goods can help prevent spoilage and overbuying.  

### Success Criteria  
To effectively measure the success of our Fridge Assistant app, focus on key metrics such as user engagement, which includes tracking the number of active users and their frequency of app usage. Assess the reduction in food waste by analyzing user-reported data on expired items, and gauge long-term waste reduction trends. User satisfaction should be evaluated through feedback surveys and app store ratings to understand user experiences and areas for improvement. Additionally, monitor financial performance through revenue from advertisements and donations, and keep an eye on operational metrics like user retention rates and customer support queries. Regularly reviewing these metrics will help optimize the app’s impact and user satisfaction.  

### Competitor Analysis  
**Strengths of Competitors**  
Established User Base: Competitors might already have a large, established user base, which can provide them with a robust dataset for improving app functionalities and user experience.  
Advanced Technology Integration: Some competitors might integrate more advanced technologies, such as AI for predictive analytics or IoT to connect with smart fridges, enhancing their usability and effectiveness.  
Brand Recognition: Larger, more recognized brands have a trust advantage that can influence user choice and loyalty, making it easier for them to attract and retain users.  

**Weaknesses of Competitors**  
Generic Features: Many competitors may offer generic solutions that are not tailored to specific user groups, which might not address the unique needs of families or co-renters specifically.  
Limited Customization: Competitors might lack customization options that allow users to tailor the app according to their specific living arrangements or preferences, which can detract from user experience and effectiveness in reducing food waste.  
Complex User Interfaces: Some apps may have complex or non-intuitive user interfaces, which can deter less tech-savvy users or those looking for quick and easy solutions to manage their food inventory.  

### Monetization Model  
Include targeted ads from food brands, supermarkets and local businesses in the app based on user data, as well as sponsored content that is seamlessly integrated into the app experience. In addition, a donation model could be implemented to allow users to make voluntary contributions, providing them with membership levels that allow them to enjoy benefits such as premium features and ad-free usage. Cross-promotions with grocery stores and paid value-added services such as personalized meal plans could further increase revenue. Maintain transparency in the use of funds to foster trust and loyalty, and engage users through feedback to align the monetization strategy with their expectations and needs.  

## Initial Design  

**Purpose**

The MVP of our Fridge Assistant app aims to provide users with a seamless experience for managing their fridge contents, reducing food waste, and facilitating household organization.

**Scope and Limitations**

The MVP will focus on basic functionalities including user authentication, fridge creation, member invitation, food inventory management, expiration date tracking, notifications/reminders, and access to recipe information. Limitations may include lack of advanced features such as barcode scanning and integrations with grocery delivery services.

### UI/UX Design
Login/Register Page: Simple email/password authentication.

First Page: Add/Create fridge with a name, invite/manage members via email.

Home Page: Intuitive layout for adding/deleting food items, editing quantities, and setting expiration dates. Alerts and notifications for reminders displayed prominently.

User Page: Profile settings including user name, email, and password management.

Info Page: Access to recipe information.

![5b09875de20172cdd0f0b02ad3a1f66](https://github.com/gly66/545GroupProject/assets/80219810/5c580c7c-6397-4bfc-bc49-5935214a6c57)

### Technical Architecture
Data Structures: Database to store user information, fridge details, and food inventory.

Storage Considerations: Cloud storage for scalable and accessible data storage.

Web/Cloud Interactions: Use of APIs for sending notifications/reminders and accessing recipe information.

Measurement of Success: Metrics may include user engagement (number of active users, frequency of app usage), reduction in food waste (tracked through user input on expired items), and user satisfaction (gathered through feedback surveys or ratings).

**Dependencies**

Email service API for user authentication and notifications.
Recipe API for accessing recipe information.
Cloud storage service for data storage and retrieval.


## Challenges and Open Questions  
**Technical Challenges**  
User Interface Usability:  
Challenge: Creating an intuitive and user-friendly interface that can be easily navigated by all user demographics, including those less tech-savvy.  
Solution: Focus on UX/UI design principles, conduct user testing with diverse groups to gather feedback, and iterate designs based on this feedback. Consider accessibility features to accommodate all users.  

Online connectivity:  
Challenge: The challenge of requiring constant online connectivity for data sharing in your fridge management app is a significant one, especially given the need for real-time synchronization among multiple users, such as family members or co-renters.  
Solution: Develop the app to primarily function online to enable real-time data sharing and updates. This ensures that all users have the most current information about the fridge contents, reducing the risk of food waste and miscommunication. Also, We can Implement offline features that allow users to access and manage the fridge inventory even without an internet connection.  

Data storage:  
Challenge: Ensuring robust data storage and management is critical for the success of your fridge management app, especially given its reliance on accurate and timely information about users' fridge contents.  
Solution: Utilize reliable cloud services for storing and managing data to ensure scalability, security, and high availability. Cloud storage will allow users to access their data from any device and facilitate easier updates and backups.  

**Open Questions**  
What features do users find most engaging in similar apps? How can we incorporate or improve these features in our app to enhance user engagement?  
Would incorporating AI for predictive analytics significantly improve user experience, and what specific predictions would be most useful (e.g., suggesting recipes based on current inventory)?  
What are the best practices for data security in apps that handle sensitive information such as food inventory and purchasing habits?  
What optimizations can be made to ensure smooth operation under varied connectivity scenarios?  

**Reference**  
[1]https://www.waste360.com/food-waste/study-shows-how-much-food-americans-waste-every-year  

