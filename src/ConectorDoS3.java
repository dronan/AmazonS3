import java.io.IOException;

public class ConectorDoS3 {

	public static void main(String[] args) {
		
		UploadArquivo upload = new UploadArquivo();
		Arquivo arq = new Arquivo();
		
		arq.setCaso(133456);
		arq.setArquivo("imagem.jpg");
		arq.setCaminho("C:/imagem.jpg");
		arq.setDestino("NOME_DO_BUCKET/teste/");

		
		try {
			upload.enviaArquivos(arq);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	


}
