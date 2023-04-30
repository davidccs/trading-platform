import { Box, Table, Thead, Tbody, Tr, Th, Td } from "@chakra-ui/react";
import React, { useEffect, useState } from "react";


interface Stock {
	id: number;
	symbol: string;
	name: string;
	price: number;
}

const MarketStocksAvailable = () => {
	const [stock, setItems] = useState<Stock[]>([]);

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
		<Box>
			<Table>
				<Thead>
					<Tr>
						<Th>Name</Th>
						<Th>Symbol</Th>
						<Th>Price ($AUD)</Th>
					</Tr>
				</Thead>
				<Tbody>
					{stock.map((item) => (
						<Tr key={item.id}>
							<Td><strong>{item.name}</strong></Td>
							<Td>{item.symbol}</Td>
							<Td>${item.price}</Td>
						</Tr>
					))}
				</Tbody>
			</Table>
		</Box>
	);
};

export default MarketStocksAvailable;
