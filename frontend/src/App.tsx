import { ChakraProvider, Container, Stack } from "@chakra-ui/react";
import * as React from "react";
import { Route, Routes } from "react-router-dom";
import Home from "./pages/Home";
import Porfolio from "./pages/Porfolio";
import Stocks from "./pages/Stocks";
import History from "./pages/History";

export const App = () => (
	<ChakraProvider>
		<Container as={Stack} maxW={"7xl"} py={10}>
			<Routes>
				<Route path="/" element={<Home />}></Route>
			</Routes>
      <Routes>
				<Route path="/stocks" element={<Stocks />}></Route>
			</Routes>
			<Routes>
				<Route path="/porfolio" element={<Porfolio />}></Route>
			</Routes>
			<Routes>
				<Route path="/history" element={<History />}></Route>
			</Routes>
		</Container>
	</ChakraProvider>
);
