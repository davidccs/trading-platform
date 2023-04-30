import React from "react";
import Footer from "../component/Footer";
import NavBar from "../component/Navbar";
import ShowStocks from "../component/ShowStocks";

const Stocks = () => {
	return (
		<>
			<NavBar></NavBar>
            <ShowStocks></ShowStocks>
			<Footer></Footer>
		</>
	);
};

export default Stocks;
