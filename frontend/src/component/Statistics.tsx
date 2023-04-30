import {
	Box,
	chakra,
	SimpleGrid,
	Stat,
	StatLabel,
	StatNumber,
	useColorModeValue,
} from "@chakra-ui/react";
import React, { useState, useEffect } from "react";

interface StatsCardProps {
	title: string;
	stat: string;
}
function StatsCard(props: StatsCardProps) {
	const { title, stat } = props;
	return (
		<Stat
			px={{ base: 4, md: 8 }}
			py={"5"}
			shadow={"xl"}
			border={"1px solid"}
			borderColor={useColorModeValue("gray.800", "gray.500")}
			rounded={"lg"}>
			<StatLabel fontWeight={"medium"} isTruncated>
				{title}
			</StatLabel>
			<StatNumber fontSize={"2xl"} fontWeight={"medium"}>
				{stat}
			</StatNumber>
		</Stat>
	);
}

interface Item {
	customerId: number;
	name: string;
	balance: number;
    portfolioCount: number;
}

export default function BasicStatistics() {
	const [items, setItems] = useState<Item[]>([]);

	useEffect(() => {
		const fetchItems = async () => {
			const url = `http://localhost:8080/api/customer/details?userId=1`;
			const response = await fetch(url);
			const data = await response.json();
			setItems(data);
		};
		fetchItems();
	}, []);
    const customerInformation = Object.values(items);

    var customerInvestCompanyCount = customerInformation[5] ?? 0

	return (
		<Box maxW="7xl" mx={"auto"} pt={5} px={{ base: 2, sm: 12, md: 17 }}>
			<chakra.h1
				textAlign={"center"}
				fontSize={"4xl"}
				py={10}
				fontWeight={"bold"}>
				Current Account Statistics
			</chakra.h1>
			<SimpleGrid
				columns={{ base: 1, md: 3 }}
				spacing={{ base: 5, lg: 8 }}>
				<StatsCard title={"Hello"} stat={customerInformation[1]?.toString() + "!"} />
				<StatsCard title={"Current Balance"} stat={"$"+ customerInformation[2]?.toString() + " (AUD)"} />
				<StatsCard
					title={"Currently invested in"}
					stat={customerInvestCompanyCount+ " Companies"}
				/>
			</SimpleGrid>
		</Box>
	);
}

