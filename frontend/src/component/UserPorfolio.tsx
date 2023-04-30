import { Box, Table, Thead, Tbody, Tr, Th, Td } from "@chakra-ui/react";
import React, { useEffect, useState } from "react";

interface Item {
	stock: Stock;
	totalValue: number;
	quantity: number;
}

interface Stock {
	id: number;
	symbol: string;
	name: string;
	price: number;
}

const UserPorfolio = () => {
	const [items, setItems] = useState<Item[]>([]);

	useEffect(() => {
		const fetchItems = async () => {
			const response = await fetch(
				"http://localhost:8080/api/customer/portfolio?userId=1"
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
						<Th>Trading Price ($AUD)</Th>
                        <Th>Quantity</Th>
					</Tr>
				</Thead>
				<Tbody>
					{items.map((item) => (
						<Tr key={item.stock.id}>
							<Td><strong>{item.stock.name}</strong></Td>
							<Td>{item.stock.symbol}</Td>
							<Td>${item.stock.price}</Td>
                            <Td>{item.quantity}x</Td>
						</Tr>
					))}
				</Tbody>
			</Table>
		</Box>
	);
};

export default UserPorfolio;
