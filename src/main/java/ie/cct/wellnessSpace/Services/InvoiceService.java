package ie.cct.wellnessSpace.Services;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfImage;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import ie.cct.wellnessSpace.Entities.Bookings;
import ie.cct.wellnessSpace.Entities.ProdBook;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
import java.io.*;
import java.text.DecimalFormat;
import java.util.List;


@Transactional
@Service
public class InvoiceService {

    /*
    Class to generate pdf for invoice using dependency itextpdf
        - Code based on tutorial by ZetCode (2019)
     */


    public static ByteArrayInputStream generatePdfInvoice(Bookings booking, List<ProdBook> prodBook) throws IOException, DocumentException {
        Document document = new Document();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {


            PdfPTable table = new PdfPTable(3);
            PdfPCell hcell;
            hcell = new PdfPCell(new Phrase("Booking Code"));
            hcell.setBackgroundColor(new BaseColor(210, 217, 221));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Booking Status"));
            hcell.setBackgroundColor(new BaseColor(210, 217, 221));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Customer"));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            hcell.setBackgroundColor(new BaseColor(210, 217, 221));
            table.addCell(hcell);

            PdfPCell cell;

            cell = new PdfPCell(new Phrase(booking.getId_booking().toString()));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(booking.getStatus().getStatus()));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(booking.getCustomer().getName() + " " + booking.getCustomer().getSurname()));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            hcell = new PdfPCell(new Phrase("Date"));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            hcell.setBackgroundColor(new BaseColor(210, 217, 221));
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Time"));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            hcell.setBackgroundColor(new BaseColor(210, 217, 221));
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Duration"));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            hcell.setBackgroundColor(new BaseColor(210, 217, 221));
            table.addCell(hcell);

            cell = new PdfPCell(new Phrase(booking.getDate().toString()));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(booking.getTime().toString()));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(booking.getTreatment().getDuration().toString()));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            hcell = new PdfPCell(new Phrase("Treatment"));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            hcell.setBackgroundColor(new BaseColor(210, 217, 221));
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Price"));
            hcell.setBackgroundColor(new BaseColor(210, 217, 221));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Professional"));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            hcell.setBackgroundColor(new BaseColor(210, 217, 221));
            table.addCell(hcell);

            cell = new PdfPCell(new Phrase(booking.getTreatment().getName()));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(String.format("%.2f",booking.getTreatment().getCost())));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(booking.getStaff().getName()+" "+booking.getStaff().getSurname()));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            PdfPTable table1 = new PdfPTable(4);
            if (prodBook != null) {


                hcell = new PdfPCell(new Phrase("Product"));
                hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
                hcell.setBackgroundColor(new BaseColor(219, 188, 186));
                table1.addCell(hcell);

                hcell = new PdfPCell(new Phrase("Quantity"));
                hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
                hcell.setBackgroundColor(new BaseColor(219, 188, 186));
                table1.addCell(hcell);

                hcell = new PdfPCell(new Phrase("Cost unit"));
                hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
                hcell.setBackgroundColor(new BaseColor(219, 188, 186));
                table1.addCell(hcell);

                hcell = new PdfPCell(new Phrase("Cost Total"));
                hcell.setBackgroundColor(new BaseColor(219, 188, 186));
                hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table1.addCell(hcell);

                for (ProdBook pd : prodBook) {
                    double total = pd.getProduct().getCost() * pd.getQuantity();
                    cell = new PdfPCell(new Phrase(pd.getProduct().getName()));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table1.addCell(cell);

                    cell = new PdfPCell(new Phrase(String.format("%d",pd.getQuantity())));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table1.addCell(cell);

                    cell = new PdfPCell(new Phrase(String.format("%.2f",pd.getProduct().getCost())));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table1.addCell(cell);

                    cell = new PdfPCell(new Phrase(String.format("%.2f",total)));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table1.addCell(cell);

                }

            }
            PdfPTable table2 = new PdfPTable(2);
            double priceTotal=0.2f;
            for (ProdBook pd : prodBook){
                priceTotal=+(pd.getQuantity()*pd.getProduct().getCost());
            }
            priceTotal = priceTotal + booking.getTreatment().getCost();

            hcell = new PdfPCell(new Phrase("Total Price"));
            hcell.setBackgroundColor(new BaseColor(210, 217, 221));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table2.addCell(hcell);

            cell = new PdfPCell(new Phrase(String.format("%.2f",priceTotal)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table2.addCell(cell);

            PdfWriter writer = PdfWriter.getInstance(document, out);
            document.open();
            document.add(table);
            document.add(table1);
            document.add(table2);
            document.close();
        } catch (DocumentException ex) {

        }
        return new ByteArrayInputStream(out.toByteArray());
    }
}
