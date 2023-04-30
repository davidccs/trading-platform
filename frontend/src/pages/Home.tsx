import React from "react";
import Footer from "../component/Footer";
import NavBar from "../component/Navbar";
import BasicStatistics from "../component/Statistics";
import IndexTable from "../component/Table";

const HomePage = () => {
	return (
		<>
			<NavBar></NavBar>
			<BasicStatistics></BasicStatistics>
			<IndexTable></IndexTable>
			<Footer></Footer>
		</>
	);
};

export default HomePage;
