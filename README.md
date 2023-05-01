# trading-platform

# Specifications:

The platform has the following requirements:
- Users can place orders to buy or sell stocks.
- Users can view their current portfolio.
- Users can view a list of all orders placed by them.
- Users can view a list of all executed orders.

# Getting started:

Run: yarn start within the frontned folder. Should be running on port 3000
Run: backend on port 8080

There should be some helper configs that are run in the backend. This should automatically load data in such as a starting balance of $10,000 and some stocks which you can purchase.

You are only able to buy the stocks shown in the Stocks section of the APP. Any other Stocks will be invalid. Purchase of stock are only allowed in the Symbol form. 


# Assumptions:
- Stock prices do not change. In the real world stock prices should theoretically change. However, the set price is the price we sell and buy at.
- If the stock price you are buying at is equal or above the current market price. A purchase can be made.
- If the stock price you are selling at is lower than the market price. It will automatically be sold. 
- Vice versa for the inverse. 
