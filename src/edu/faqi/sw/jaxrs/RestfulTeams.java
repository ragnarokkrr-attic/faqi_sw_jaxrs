package edu.faqi.sw.jaxrs;

import java.util.Map;

import javax.annotation.Resource;
import javax.xml.transform.Source;
import javax.xml.ws.BindingType;
import javax.xml.ws.Provider;
import javax.xml.ws.ServiceMode;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.WebServiceProvider;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.http.HTTPBinding;
import javax.xml.ws.http.HTTPException;

/**
 * Eh um WebServiceProvider Generico ao inves do SOAP
 * 
 * @author ragnarokkrr
 * 
 */
@WebServiceProvider
//
// Message diz que vamos manipular tanto o conteudo da requisicao
// como os proprios cabecalhos HTTP e o corpo
@ServiceMode(value = javax.xml.ws.Service.Mode.MESSAGE)
// Ao inves de vincular ao protocolo SOAP trabalhaomos diretamente
// com o protocolo HTTP
@BindingType(value = HTTPBinding.HTTP_BINDING)
// Implementacao generica de BAIXO NIVEL de um servico
public class RestfulTeams implements Provider<Source> {

	@Resource
	protected WebServiceContext ws_ctx;

	private Map<String, Team> teamMap; // fonte de dados

	private byte[] teamBytes; // arquivo em disco

	private static final String FNAME = "teams.dat";

	/*
	 * Manipula requisicoes e gera mensagem de saida
	 * 
	 * @see javax.xml.ws.Provider#invoke(java.lang.Object)
	 */
	@Override
	public Source invoke(Source request) {
		if (ws_ctx == null) {
			throw new RuntimeException("Nao consequi contexto de WS");
		}

		if (request == null) {
			System.out.println("Requisicao Nul");
		} else {
			System.out.println("Requisicao com conteudo");
		}
		// pega do contexto o verbo da requisicao
		MessageContext messageContext = ws_ctx.getMessageContext();
		String httpVerb = (String) messageContext
				.get(MessageContext.HTTP_REQUEST_METHOD);
		httpVerb = httpVerb.trim().toUpperCase();

		if ("GET".equals(httpVerb)) {
			return null;
		} else if ("DELETE".equals(httpVerb)) {
			return null;
		} else if ("POST".equals(httpVerb)) {
			return null;
		} else if ("PUT".equals(httpVerb)) {
			return null;
		}

		throw new HTTPException(405);// Verbo HTTP nao tratado
	}

	public RestfulTeams() {
		reaTeamsFromFile();
		deserialize();
	}

	private void deserialize() {
		// TODO Auto-generated method stub

	}

	private void reaTeamsFromFile() {
		// TODO Auto-generated method stub

	}

}
