import {
	Box,
	Button,
	Flex,
	FormControl,
	FormLabel,
	Heading,
	HStack,
	Input,
	Radio,
	RadioGroup, SimpleGrid, Stack, Text, useColorModeValue
} from "@chakra-ui/react";
import React, { useEffect, useState } from "react";
import UserPorfolio from "./UserPorfolio";


interface Item {
	customerId: number;
	name: string;
	balance: number;
    portfolioCount: number;
}

export default function SignupCard() {
	const [symbol, setSymbol] = useState("");
	const [price, setPrice] = useState("");
	const [quantity, setQuantity] = useState("");
	const [orderType, setValue] = useState("BUY");

	console.log("Symbol:", symbol, "Price:", price, "Quantity:", quantity);

	const handleSymbolChange = (event: React.ChangeEvent<HTMLInputElement>) => {
		setSymbol(event.target.value);
	};

	const handlePriceChange = (event: React.ChangeEvent<HTMLInputElement>) => {
		setPrice(event.target.value);
	};

	const handleQuantityChange = (
		event: React.ChangeEvent<HTMLInputElement>
	) => {
		setQuantity(event.target.value);
	};

	const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
		const formData = {
			symbol: symbol,
			price: price,
			quantity: quantity,
		}

		var transactionType = orderType.toLowerCase();

		const url = `http://localhost:8080/api/stocks/${transactionType}?symbol=${symbol}&price=${price}&quantity=${quantity}`

		e.preventDefault();
		try {
			const response = await fetch(
				url,
				{
					method: "POST",
					headers: { "Content-Type": "application/json" },
					body: JSON.stringify(formData),
				}
			);
			console.log("Response:", response);
		} catch (error) {
			console.error(error);
		}
	};

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

	return (
		<Flex
			align={"center"}
			justify={"center"}
			bg={useColorModeValue("gray.50", "gray.800")}>
			<form onSubmit={handleSubmit}>
			<SimpleGrid columns={{ base: 1, md: 2 }} spacing={10}>
				<Stack spacing={8} mx={"auto"} maxW={"lg"} py={12} px={6}>
				<Stack align={"center"}>
						<Heading fontSize={"4xl"} textAlign={"center"}>
							Currently Holding
						</Heading>
					</Stack>
				<UserPorfolio></UserPorfolio>
				</Stack>
				<Stack spacing={8} mx={"auto"} maxW={"lg"} py={12} px={6}>
					<Stack align={"center"}>
						<Heading fontSize={"4xl"} textAlign={"center"}>
							Buying or Selling?
						</Heading>
						
						<Text fontSize='6xs'>Current Funds: ${customerInformation[2]?.toString()} (AUD) </Text>
					</Stack>
					<Box
						rounded={"lg"}
						bg={useColorModeValue("white", "gray.700")}
						boxShadow={"lg"}
						p={8}>
						<Stack spacing={4}>
							<RadioGroup onChange={setValue} value={orderType}>
								<HStack>
									<Stack spacing={4} direction="row">
										<Radio value="BUY">Buy</Radio>
										<Radio value="SELL">Sell</Radio>
									</Stack>
								</HStack>
							</RadioGroup>
							<HStack>
								<Box>
									<FormControl id="Symbol">
										<FormLabel>Symbol</FormLabel>
										<Input
											required
											type="text"
											value={symbol}
											onChange={handleSymbolChange}
										/>
									</FormControl>
								</Box>
								<Box>
									<FormControl id="Quantity">
										<FormLabel>Quantity</FormLabel>
										<Input
											required
											type="number"
											value={quantity}
											onChange={handleQuantityChange}
										/>
									</FormControl>
								</Box>
							</HStack>
							<FormControl id="price">
								<FormLabel>$ Price</FormLabel>
								<Input
									required
									type="number"
									value={price}
									onChange={handlePriceChange}
								/>
							</FormControl>

							<Stack spacing={10} pt={2}>
								<Button
									type="submit"
									loadingText="Submitting"
									size="lg"
									bg={"green.400"}
									color={"white"}
									_hover={{
										bg: "green.500",
									}}
									onClick={() => window.location.reload()}>
									{orderType}
								</Button>
							</Stack>
						</Stack>
					</Box>
				</Stack>
				</SimpleGrid>

			</form>
		</Flex>
	);
}
