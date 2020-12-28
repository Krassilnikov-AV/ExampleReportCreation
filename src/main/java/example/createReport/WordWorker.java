package example.createReport;

import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;

import java.io.*;
import java.util.*;

/**
 * класс позволяет создать документ Word, в котором выполняется: - создание
 * верхнего и нижнего колонтитура
 *
 * @author Aleks
 */
public class WordWorker {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		try {
			// создаем модель docx документа,
			// к которой будем прикручивать наполнение (колонтитулы, текст)
			XWPFDocument docxModel = new XWPFDocument();
			CTSectPr ctSectPr = docxModel.getDocument().getBody().addNewSectPr();
			// получаем экземпляр XWPFHeaderFooterPolicy для работы с колонтитулами
			XWPFHeaderFooterPolicy headerFooterPolicy = new XWPFHeaderFooterPolicy(docxModel, ctSectPr);

			// создаем верхний колонтитул Word файла
			CTP ctpHeaderModel = createHeaderModel("Верхний колонтитул - можно заполнить!");
			// устанавливаем сформированный верхний
			// колонтитул в модель документа Word
			XWPFParagraph headerParagraph = new XWPFParagraph(ctpHeaderModel, docxModel);
			headerFooterPolicy.createHeader(XWPFHeaderFooterPolicy.DEFAULT, new XWPFParagraph[]{headerParagraph});

			// создаем нижний колонтитул docx файла
			CTP ctpFooterModel = createFooterModel("Просто нижний колонтитул");
			// устанавливаем сформированый нижний
			// колонтитул в модель документа Word
			XWPFParagraph footerParagraph = new XWPFParagraph(ctpFooterModel, docxModel);
			headerFooterPolicy.createFooter(XWPFHeaderFooterPolicy.DEFAULT, new XWPFParagraph[]{footerParagraph});

			// создаем обычный параграф, который будет расположен слева,
			// будет синим курсивом со шрифтом 14 размера
			XWPFParagraph bodyParagraph = docxModel.createParagraph();
			bodyParagraph.setAlignment(ParagraphAlignment.LEFT);
			XWPFRun paragraphConfig = bodyParagraph.createRun();
			paragraphConfig.setItalic(true);
			paragraphConfig.setFontSize(14);
			// HEX цвет без решетки #
			paragraphConfig.setColor("06357a");
			paragraphConfig.setText("дОБРО ПОЖАЛОВАТЬ!");

			List<String> contents = new ArrayList<String>();
			contents.add("John");
			contents.add("Maikle");
			contents.add("Sanya");
			contents.add("Nike");

			int n = contents.size();

			XWPFTable table = docxModel.createTable();
			for (int i = 0; i < n; i++) {
				table.createRow().getCell(0).setText(contents.get(i));
			}

			// сохраняем модель docx документа в файл
			OutputStream outputStream
				= new FileOutputStream("D:\\REPOSITORIES-2\\WordTest.docx");
			docxModel.write(outputStream);
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Файл успешно создан!");
	}

	private static CTP createFooterModel(String footerContent) {
		// создаем футер или нижний колонтитул
		CTP ctpFooterModel = CTP.Factory.newInstance();
		CTR ctrFooterModel = ctpFooterModel.addNewR();
		CTText cttFooter = ctrFooterModel.addNewT();

		cttFooter.setStringValue(footerContent);
		return ctpFooterModel;
	}

	private static CTP createHeaderModel(String headerContent) {
		// создаем хедер или верхний колонтитул
		CTP ctpHeaderModel = CTP.Factory.newInstance();
		CTR ctrHeaderModel = ctpHeaderModel.addNewR();
		CTText cttHeader = ctrHeaderModel.addNewT();

		cttHeader.setStringValue(headerContent);
		return ctpHeaderModel;
	}
}
