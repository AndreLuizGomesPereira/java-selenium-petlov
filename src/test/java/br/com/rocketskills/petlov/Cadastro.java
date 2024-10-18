package br.com.rocketskills.petlov;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

class PontoDoacao {
	String nome;
	String email;
	String cep;
	String numero;
	String complemento;
	String pets;

	public PontoDoacao(String nome, String email, String cep, String numero, String complemento, String pets) {
		this.nome = nome;
		this.email = email;
		this.cep = cep;
		this.numero = numero;
		this.complemento = complemento;
		this.pets = pets;
	}
}

class Cadastro {

	private void submeterFormulario(PontoDoacao ponto) {
		open("https://petlov.vercel.app/signup");
		$("h1").shouldHave(text("Cadastro de ponto de doação"));
		$("input[name=name]").setValue(ponto.nome);
		$("input[name=email]").setValue(ponto.email);
		$("input[name=cep]").setValue(ponto.cep);
		$("input[value='Buscar CEP']").click();
		$("input[name=addressNumber]").setValue(ponto.numero);
		$("input[name=addressDetails]").setValue(ponto.complemento);
		$(By.xpath("//span[text()='" + ponto.pets + "']")).click();
		$(By.className("button-register")).click();
	}

	@Test
	@DisplayName("Deve cadsatrar um novo ponto de doação")
	void criarPonto() {

		PontoDoacao ponto = new PontoDoacao("Andre's House", "andre@andreshouse.com.br", "45990000", "1000", "Bloco 5F",
				"Cachorros");

		submeterFormulario(ponto);
		$("#success-page h1").shouldHave(text("Você fez a diferença!"));
	}

	@Test
	@DisplayName("Não deve cadsatrar um novo ponto de doação com e-mail inválido")
	void emailInvalido() {

		PontoDoacao ponto = new PontoDoacao("Paraíso dos Pets", "paraiso#paraisodospets.com.br", "45990000", "1000",
				"Bloco 5F",
				"Cachorros");

		submeterFormulario(ponto);
		$(".alert-error").shouldHave(text("Informe um email válido"));
	}
}
