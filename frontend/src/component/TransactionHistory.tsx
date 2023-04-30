import { Table, Thead, Tr, Th, Tbody, Td, Box } from "@chakra-ui/react";
import React, { useEffect, useState } from "react";

interface Stock {
	id: number;
	symbol: string;
	name: string;
	price: number;
}

interface Item {
	id: number;
	stock: Stock;
	type: string;
	executed: string;
	quantity: number;
	orderPrice: number;
	salePrice: number;
	createdDate: string;
}

interface TransactionHistoryProps {
	isExecuted: boolean;
}

const TransactionHistory = ({ isExecuted }: TransactionHistoryProps) => {
	const [data, setData] = useState<Item[]>([]);

	useEffect(() => {
		const fetchData = async () => {
			try {
				const response = await fetch(
					"http://localhost:8080/api/customer/orders?userId=1"
				);
				const jsonData = await response.json();
				setData(jsonData);
			} catch (error) {
				console.error(error);
			}
		};

		fetchData();
	}, []);

	return (
		<>
			{isExecuted ? (
				<Box>
					<Table>
						<Thead>
							<Tr>
								<Th>Name</Th>
								<Th>Symbol</Th>
								<Th>Type</Th>
								<Th>Quantity</Th>
								<Th>Order Price ($AUD)</Th>
                                <Th>Sale Price ($AUD)</Th>
								<Th>Transaction Time</Th>
							</Tr>
						</Thead>
						<Tbody>
							{data.map(
								(item) =>
									item.executed === "FULFILLED" && (
										<Tr key={item.id}>
											<Td>
												<strong>
													{item.stock.name}
												</strong>
											</Td>
											<Td>{item.stock.symbol}</Td>
											<Td>
												<strong>{item.type}</strong>
											</Td>
											<Td>{item.quantity}x</Td>
											<Td>${item.orderPrice}</Td>
                                            <Td>{item.salePrice ? `$${item.salePrice}` : null}</Td>
											<Td>{item.createdDate}</Td>
										</Tr>
									)
							)}
						</Tbody>
					</Table>
				</Box>
			) : (
				<Box>
					<Table>
						<Thead>
							<Tr>
								<Th>Name</Th>
								<Th>Symbol</Th>
								<Th>Type</Th>
								<Th>Quantity</Th>
								<Th>Order Price ($AUD)</Th>
                                <Th>Sale Price ($AUD)</Th>
								<Th>Transaction Time</Th>
							</Tr>
						</Thead>
						<Tbody>
							{data.map((item) => (
								<Tr key={item.id}>
									<Td>
										<strong>{item.stock.name}</strong>
									</Td>
									<Td>{item.stock.symbol}</Td>
									<Td>
										<strong>{item.type}</strong>
									</Td>
									<Td>{item.quantity}x</Td>
									<Td>${item.orderPrice}</Td>
                                    <Td>{item.salePrice ? `$${item.salePrice}` : null}</Td>
									<Td>{item.createdDate}</Td>
								</Tr>
							))}
						</Tbody>
					</Table>
				</Box>
			)}
		</>
	);
};

export default TransactionHistory;
