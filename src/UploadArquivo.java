import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.internal.Constants;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ProgressEvent;
import com.amazonaws.services.s3.model.ProgressListener;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.StorageClass;

public class UploadArquivo {
	String myFile = "teste.jpg";
	String bucketName = "NOME_DO_BUCKET";

	void enviaArquivos(final Arquivo arq) throws IOException {
		InputStream credentials = UploadArquivo.class
				.getResourceAsStream("AwsCredentials.properties");
		PropertiesCredentials awsCredentials = new PropertiesCredentials(
				credentials);

		AmazonS3 s3 = new AmazonS3Client(awsCredentials);


		
		
			try {
				/*
				s3.putObject(new PutObjectRequest(arq.getDestino() + arq.getCaso(), arq
						.getArquivo(), new File(arq.getCaminho()))
						.withCannedAcl(CannedAccessControlList.PublicRead));
				*/
				
				
				
	
				PutObjectRequest putObjectRequest = new PutObjectRequest(arq.getDestino() + arq.getCaso(), arq
						.getArquivo(), new File(arq.getCaminho()))
						.withCannedAcl(CannedAccessControlList.PublicRead)
						.withStorageClass(StorageClass.ReducedRedundancy);
							
						 putObjectRequest
                         .setProgressListener(new ProgressListener() {
                        	 
                        	 File file = new File(arq.getCaminho());
                        	 long tamanhoTotalArquivo  = file.length();
                        	 long tamanhoArquivo  = file.length();
                             long totalEnviado = 0;
                        	 @Override
                             public void progressChanged(
                                     ProgressEvent progressEvent) {
                        		 
                        		 // restante:
                            	 //tamanhoArquivo -= progressEvent.getBytesTransfered();
                           		 //System.out.println((tamanhoArquivo * 100) / tamanhoTotalArquivo + "% restantes");  // quantidade de bytes enviados
                           
                        		 // enviado:
                        		 totalEnviado += progressEvent.getBytesTransfered();
                        		 //System.out.println(tamanhoTotalArquivo);
                           		 System.out.println(((totalEnviado * 100 ) / tamanhoTotalArquivo) + "% enviado");  // quantidade de bytes enviados
                                                   		 
                        		 
                                 if (progressEvent.getEventCode() == ProgressEvent.COMPLETED_EVENT_CODE) {
                                     //System.out.println("Upload complete!!!");
                                 }
                                 
                             }

                         });
                 s3.putObject(putObjectRequest);
                 
                 
						
			} catch (Exception e) {
				throw new RuntimeException();
			} finally {
				System.out.println("Upload do arquivo " + arq.getArquivo()
						+ " finalizado!");
			} 
						
		
		
/*
				
*/
		// s3.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);


	}

}
