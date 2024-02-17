package com.shop.soldphonev2.api.report;

import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.DeviceGray;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.DashedBorder;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;

import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.shop.soldphonev2.api.report.web.ProductDto;
import com.shop.soldphonev2.api.report.web.UserDto;
import com.shop.soldphonev2.base.BaseResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GeneratePdfServiceImp implements GeneratePdfService{

    public final GeneratePdfMapStruct generatePdfMapStruct;
    Color grayColor = new DeviceGray(0.5f);
    float[] rgbColor = {0, 0, 0}; // RGB values for black
    Color blackColor = new DeviceRgb(rgbColor[0], rgbColor[1], rgbColor[2]);
    float[] rgbColor1 = {1, 1, 1}; // RGB values for white
    Color whiteColor = new DeviceRgb(rgbColor1[0], rgbColor1[1], rgbColor1[2]);


    @Override
    public BaseResponseMessage generate(List<ProductDto> productDtos,UserDto userDto) throws FileNotFoundException {

        List<ProductReport> productReportList = generatePdfMapStruct.create(productDtos);
        ProductReport productReportUser = generatePdfMapStruct.user(userDto);

// Define the pattern for the timestamp
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HHmmss");

        // Get the current LocalDateTime
        LocalDateTime now = LocalDateTime.now();

        // Format the timestamp as a string
        String timestamp = now.format(formatter);

        // Generate the filename with the timestamp
        String tempPath = "invoice_" + timestamp + ".pdf";
        String username = System.getProperty("user.name");
        String finalPath = "C:/Users/" + username + "/Downloads/"+tempPath+".pdf";

        try (PdfWriter pdfWriter = new PdfWriter(tempPath)) {
            try (PdfDocument pdfDocument = new PdfDocument(pdfWriter)) {
                pdfDocument.setDefaultPageSize(PageSize.A4);

                try (Document document = new Document(pdfDocument)) {
                    float treecol=190f;
                    float twocol =285f;
                    float twocol150=twocol+150f;
                    float twocolWidth[] = {twocol150,twocol};
                    float fullWidth []={treecol*3};
                    float threeColTable[]={treecol,treecol,treecol};
                    Paragraph onesp = new Paragraph("\n");

                    Table table = new Table(twocolWidth);
                    table.addCell(new Cell().add(new Paragraph("Invoice")).setFontSize(20f).setBorder(Border.NO_BORDER).setBold());
                    Table nestedTable=new Table(new float[]{twocol/2,twocol/2});
                    nestedTable.addCell(new Cell().add(getHeaderTextCell("Invoice No.")).setBorder(Border.NO_BORDER));
                    nestedTable.addCell(new Cell().add(getHeaderTextCell(generateInvoiceNumber())).setBorder(Border.NO_BORDER));
                    nestedTable.addCell(new Cell().add(getHeaderTextCell("Invoice Date.")).setBorder(Border.NO_BORDER));
                    nestedTable.addCell(new Cell().add(getHeaderTextCell(timestamp)).setBorder(Border.NO_BORDER));


                    table.addCell(new Cell().add(nestedTable).setBorder(Border.NO_BORDER));


                    Border gb = new SolidBorder(grayColor,2f);
                    Table divider = new Table(fullWidth);
                    divider.setBorder(gb);

                    document.add(table);
                    document.add(onesp);
                    document.add(divider);
                    document.add(onesp);


                    Table twoColTable=new Table(twocolWidth);
                    twoColTable.addCell(getCell10fLeft("Billing Information",true));
                    twoColTable.addCell(getCell10fLeft("Shipping Information",true));
                    document.add(twoColTable.setMarginBottom(12f));

                    //iNFO FIRST ROW
                    Table twoColTable2=new Table(twocolWidth);
                    twoColTable2.addCell(getCell10fLeft("Company",true));
                    twoColTable2.addCell(getCell10fLeft("Name ",true));
                    twoColTable2.addCell(getCell10fLeft("Code Error",false));
                    twoColTable2.addCell(getCell10fLeft(productReportUser.getName(),false));
                    document.add(twoColTable2);


                    Table twoColTable3=new Table(twocolWidth);
                    twoColTable3.addCell(getCell10fLeft("Name",true)).setPaddingLeft(-12f);
                    twoColTable3.addCell(getCell10fLeft("Phone Number",true));
                    twoColTable3.addCell(getCell10fLeft("Code Error",false));
                    twoColTable3.addCell(getCell10fLeft(productReportUser.getPhoneNumber(),false));
                    document.add(twoColTable3);

                    float oneColoumnwidth[]={twocol150};
                    Table oneColTable1=new Table(oneColoumnwidth);
                    oneColTable1.addCell(getCell10fLeft("Address",true));
                    oneColTable1.addCell(getCell10fLeft("kankav,Baray,ddjjjasj\nDunkev,takeo",false));
                    oneColTable1.addCell(getCell10fLeft("Email",true));
                    oneColTable1.addCell(getCell10fLeft("Nganvidy@gmail.com",false));
                    document.add(oneColTable1.setMarginBottom(10f));
//                  document.add(fullwidthDashedBorder(fullwidth));


                    Table tabledivider2= new Table(threeColTable);
                    Border dgb = new DashedBorder(grayColor,0.5f);
                    tabledivider2.setBorder(dgb);
                    document.add(tabledivider2);

                    Paragraph productPara = new Paragraph("Product");
                    document.add(productPara.setBold());


                    Table threeColumTable1 = new Table(threeColTable);

                    threeColumTable1.setBackgroundColor(blackColor, 0.7f);

                    threeColumTable1.addCell(new Cell().add(new Paragraph("Description")).setBold().setFontColor(whiteColor).setBorder(Border.NO_BORDER));
                    threeColumTable1.addCell(new Cell().add(new Paragraph("Quantity")).setBold().setFontColor(whiteColor).setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER));
                    threeColumTable1.addCell(new Cell().add(new Paragraph("Price")).setBold().setFontColor(whiteColor).setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER));
                    document.add(threeColumTable1);


                    Table threeColumTable2 = new Table(threeColTable);
                    Float totalSum = 0f;
                    for(ProductReport productReport : productReportList){
                        float total = productReport.getQuantity()*productReport.getPricePerPiece();
                        totalSum+=total;
                        threeColumTable2.addCell(new Cell().add(new Paragraph(productReport.getProductName())).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT).setMarginLeft(10f));
                        threeColumTable2.addCell(new Cell().add(new Paragraph(String.valueOf(productReport.getQuantity()))).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER));
                        threeColumTable2.addCell(new Cell().add(new Paragraph(String.valueOf("$"+productReport.getPricePerPiece()))).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT).setMarginRight(15f));

                    }
                    document.add(threeColumTable2.setMarginBottom(20f));
                    float onetwo[]= {treecol+125f,treecol*2};
                    Table threeColorTable4 = new Table(onetwo);
                    threeColorTable4.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
                    threeColorTable4.addCell(new Cell().add(tabledivider2).setBorder(Border.NO_BORDER));
                    document.add(threeColorTable4);

                    Table threeColumTable3 = new Table(threeColTable);
                    threeColumTable3.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT).setMarginLeft(10f));
                    threeColumTable3.addCell(new Cell().add(new Paragraph("Total")).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER));
                    threeColumTable3.addCell(new Cell().add(new Paragraph(String.valueOf("$"+totalSum))).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT).setMarginRight(15f));
                    document.add(threeColumTable3);
                    document.add(tabledivider2.setMarginBottom(20f));
                    document.add(onesp);
                    document.add(divider);

                    Table footerColum = new Table(fullWidth);
                    footerColum.addCell(new Cell().add(new Paragraph("1. The Seller shall not be liable to the Buyer directly or indirectly for any loss or damage suffered by the Buyer.")).setBorder(Border.NO_BORDER));
                    footerColum.addCell(new Cell().add(new Paragraph("2. The Seller warrants the product for one (1) year from the date of shipment")).setBorder(Border.NO_BORDER));
                    document.add(onesp);
                    document.add(footerColum);
                    document.close();
                }


                // Move the file to the final destination
                Files.move(Path.of(tempPath), Path.of(finalPath), StandardCopyOption.REPLACE_EXISTING);

                // Create and return a BaseResponseMessage for success
                return new BaseResponseMessage()
                        .setMessage("PDF generated successfully and downloaded to " + finalPath)
                        .setStatus(true)
                        .setCode(String.valueOf(HttpStatus.OK.value()))
                        .setTimestamp(LocalDateTime.now());
            }
        } catch (Exception e) {
            e.printStackTrace();

            // Create and return a BaseResponseMessage for failure
            return new BaseResponseMessage()
                    .setMessage("Failed to generate or download PDF")
                    .setStatus(false)
                    .setCode(String.valueOf(HttpStatus.EXPECTATION_FAILED.value()))
                    .setTimestamp(LocalDateTime.now());
        }
    }
    static Cell getHeaderTextCell(String textValue)
    {
        return new Cell().add(new Paragraph(textValue)).setBold().setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT);
    }
    static  Cell getCell10fLeft(String textValue,Boolean isBold){
        Cell myCell=new Cell().add(new Paragraph(textValue)).setBorder(Border.NO_BORDER).setFontSize(10f).setTextAlignment(TextAlignment.LEFT);
        return  isBold ?myCell.setBold():myCell;

    }
    public static String generateInvoiceNumber() {
        String prefix = "INV";
        String currentDate = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String uniqueId = UUID.randomUUID().toString().replace("-", "").substring(0, 4);
        return prefix + currentDate + uniqueId;
    }

}
