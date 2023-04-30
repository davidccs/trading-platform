import {
	Box,
	Button,
	Container,
	Heading,
	Icon,
	Stack,
	Text,
	useColorModeValue,
} from "@chakra-ui/react";
import React from "react";
import { useEffect, useState } from "react";

interface Item {
	id: number;
	symbol: string;
	name: string;
	price: number;
}

const MyTable = () => {
	const [items, setItems] = useState<Item[]>([]);

	useEffect(() => {
		const fetchItems = async () => {
			const response = await fetch(
				"http://localhost:8080/api/stocks/all"
			);
			const data = await response.json();
			setItems(data);
		};
		fetchItems();
	}, []);

	return (
		<Container
			maxW="7xl"
			mx={"auto"}
			pt={5}
			px={{ base: 2, sm: 12, md: 17 }}>
			<Stack
				as={Box}
				textAlign={"center"}
				spacing={{ base: 8, md: 14 }}
				py={{ base: 20, md: 36 }}>
				<Heading
					fontWeight={600}
					fontSize={{ base: "2xl", sm: "4xl", md: "6xl" }}
					lineHeight={"110%"}>
					Start Investing <br />
					<Text as={"span"} color={"green.400"}>
						your future
					</Text>
				</Heading>
				<Text color={"gray.500"}>
					"I will tell you how to become rich. Close the doors. Be
					fearful when others are greedy. Be greedy when others are
					fearful." — Warren Buffett
				</Text>
			</Stack>
		</Container>
	);
};

export default MyTable;
