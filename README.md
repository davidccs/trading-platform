# Share Trading Platform

<img width="1333" alt="image" src="https://user-images.githubusercontent.com/25177664/235386145-6950008a-6fd9-4813-a592-545092bae5f7.png">

# Description:

The platform is a stock trading platform. Users of the platform have the ability to place orders to buy or sell stocks. Additionally, users can view their current portfolio of stocks, as well as view a list of all orders they have placed. The platform also allows users to view a list of all executed orders, providing them with insight into how their trades have been executed.

# Features:

The platform has the following requirements:
- Users can place orders to buy or sell stocks.
- Users can view their current portfolio.
- Users can view a list of all orders placed by them.
- Users can view a list of all executed orders.


# Technology Stack:

- Frontend:
  - Typescript, React

- Backend:
  - Java, Spring Boot

# Getting started:

**Run**: yarn start within the frontned folder. Should be running on port 3000

**Run**: backend on port 8080

There should be some helper configs that are run in the backend. This should automatically load data in such as a starting balance of **$10,000** and some stocks which you can purchase.

You are only able to buy the stocks shown in the Stocks section of the APP. Any other Stocks will be invalid. Purchase of stock are only allowed in the Symbol form. 

To deal with cors error locally the use of "@CrossOrigin(origins = "http://localhost:3000")" was required on the backend for the controller. 

# Assumptions:
- Stock prices do not change. In the real world stock prices should theoretically change. However, the set price is the price we sell and buy at.
- If the stock price you are buying at is equal or above the current market price. A purchase can be made.
- If the stock price you are selling at is lower than the market price. It will automatically be sold. 
- Vice versa for the inverse. 
- There is only one user available and you cannot add more users.
