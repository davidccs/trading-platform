import {
    Box,
    Button,
    Fade,
    Flex,
    Heading,
    Select, Stack,
    useColorModeValue,
    useDisclosure
} from "@chakra-ui/react";
import React, { useState } from "react";
import TransactionHistory from "./TransactionHistory";

export default function OrderHistory() {
	const { isOpen, onToggle } = useDisclosure();

    const [selectedValue, setSelectedValue] = useState('option1');

    const handleChange = (event: React.ChangeEvent<HTMLSelectElement>) => {
        setSelectedValue(event.target.value);
    };

	return (
		<Flex
			align={"center"}
			justify={"center"}
			bg={useColorModeValue("gray.50", "gray.800")}>
			<Stack spacing={8} mx={"auto"} maxW={"lg"} py={12} px={6}>
				<Stack>
					<Heading fontSize={"4xl"} textAlign={"center"}>
						Transaction History
					</Heading>
				</Stack>
                <Select value={selectedValue} onChange={handleChange}>
					<option value="option1">Orders Placed</option>
					<option value="option2">Executed</option>
				</Select>

				<>
					<Button onClick={onToggle}>Show Transactions</Button>
					<Fade in={isOpen}>
						<Box>
                            {selectedValue === "option1" ? (
							<TransactionHistory
								isExecuted={false}></TransactionHistory>
                            ):
                            <TransactionHistory
                            isExecuted={true}></TransactionHistory> }
						</Box>
					</Fade>
				</>
			</Stack>
		</Flex>
	);
}
