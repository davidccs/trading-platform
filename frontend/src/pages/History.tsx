import React from "react";
import Footer from "../component/Footer";
import NavBar from "../component/Navbar";
import OrderHistory from "../component/OrderHistory";

const History = () => {
	return (
		<>
			<NavBar></NavBar>
            <OrderHistory></OrderHistory>
			<Footer></Footer>
		</>
	);
};

export default History;
