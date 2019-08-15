package br.com.bspavanelli.web.utilities.enums;

public enum WebBrowser {
	CHROME("Chrome"), FIREFOX("Firefox"), INTERNET_EXPLORER("Internet Explorer"), EDGE("Edge"), ALL("All Browsers");

	private String valor;

	WebBrowser(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}

	public static WebBrowser valueOfIgnoreCase(String value) {
		return valueOf(value.toUpperCase());
	}
}
