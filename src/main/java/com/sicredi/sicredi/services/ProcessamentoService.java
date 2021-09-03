package com.sicredi.sicredi.services;

import com.sicredi.sicredi.entities.Conta;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class ProcessamentoService {
    ReceitaService receitaService = new ReceitaService();

    public void processarCsv(String caminho) {
        List<Conta> list = new ArrayList<Conta>();

        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
            String line = br.readLine();
            line = br.readLine();
            while (line != null) {
                String[] vect = line.split(";");
                String agencia = vect[0];
                String conta = vect[1];
                double saldo = Double.valueOf((vect[2]).replace(",", "."));
                String status = vect[3];
                Conta cont = new Conta();

                cont.setAgencia(agencia);
                cont.setConta(conta);
                cont.setSaldo(saldo);
                cont.setStatus(status);

                if (receitaService.atualizarConta(agencia, conta, saldo, status)) {
                    cont.setResultado("APROVADA");
                    list.add(cont);
                } else {
                    //  Conta cont = new Conta(agencia, conta, saldo, status);
                    cont.setResultado("REPROVADA");
                    list.add(cont);
                }
                line = br.readLine();
            }

        } catch (IOException | InterruptedException e) {
            System.out.println("Error: " + e.getMessage());
        }
        try {
            FileWriter arq = new FileWriter("C:\\tmp\\sicredi\\processado\\saida.csv");
            PrintWriter gravarArq = new PrintWriter(arq);
            gravarArq.println("Agencia;Conta;Saldo;Status;Resultado");
            for (int i = 0; i < list.size(); i++) {
                gravarArq.println(list.get(i).getAgencia() + ";" + list.get(i).getConta()
                        + ";" + list.get(i).getSaldoFormat()
                        + ";" + list.get(i).getStatus()
                        + ";" + list.get(i).getResultado());
            }
            gravarArq.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {

        }

    }

}
