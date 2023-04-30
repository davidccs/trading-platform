import {
    Flex, Heading, Stack,
    useColorModeValue
} from "@chakra-ui/react";
import React from "react";
import MarketStocksAvailable from "./MarketStocksAvailable";

export default function ShowStocks() {

	return (
		<Flex
			align={"center"}
			justify={"center"}
			bg={useColorModeValue("gray.50", "gray.800")}>
				<Stack spacing={8} mx={"auto"} maxW={"lg"} py={12} px={6}>
				<Stack align={"center"}>
						<Heading fontSize={"4xl"} textAlign={"center"}>
							Current Market
						</Heading>
					</Stack>
				<MarketStocksAvailable></MarketStocksAvailable>
				</Stack>
		</Flex>
	);
}
