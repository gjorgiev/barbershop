package com.example.barbershop.transaction;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthGetTransactionReceipt;
import org.web3j.protocol.http.HttpService;

import java.util.concurrent.ExecutionException;

@Controller
@RequiredArgsConstructor
public class TransactionController {

    @GetMapping("/transaction")
    public String transaction(Model model) {
        model.addAttribute("transaction", new Transaction());
        return "transaction/transaction_search";
    }

    @PostMapping("/transaction")
    public String transaction_post(Model model, @ModelAttribute Transaction transaction) throws ExecutionException, InterruptedException{
        Web3j web3j = Web3j.build(new HttpService("https://bsc-dataseed.binance.org/"));
        EthGetTransactionReceipt receipt = web3j.ethGetTransactionReceipt(transaction.getHash())
                .sendAsync()
                .get();
        model.addAttribute("receipt_from", receipt.getResult().getFrom());
        model.addAttribute("receipt_to", receipt.getResult().getTo());
        model.addAttribute("receipt_status", receipt.getResult().isStatusOK());
        return "transaction/transaction_view";
    }
}
