package kkkk;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

import java.awt.Color;
import java.awt.image.BufferedImage;
import static java.lang.System.out;

/*
 * Class JuliaSet create Julia set fractals for input constant ComplexNumber
 * @author Michał
*/
public class JuliaSet
{
	public static void main(String args[])throws IOException
	{
		//Ustawiamy zmienne wysokości i szerokości obrazu.
		double WIDTH = 800;
		double HEIGHT = 800;
		
		
		//Ustawiamy nasycenie na maksimum.
		float Saturation = 1f;
		
		// Tworzmy pusty obraz RGB do rysowania fraktala
		BufferedImage img = new BufferedImage((int)WIDTH, (int)HEIGHT,BufferedImage.TYPE_3BYTE_BGR);
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		// pobieramy stałą dla której rysujemy fraktal
		out.print("Re(c): ");
		double cReal = Double.parseDouble(reader.readLine());
		out.print("Im(c): ");
		double cImag = Double.parseDouble(reader.readLine());
		
		// Tworzymy stała liczbę zespoloną z pobranych wartości
		ComplexNumber constant = new ComplexNumber(cReal,cImag);
		
		//Ustalanie maksymalnej itecji. wpływa na czas wykonania programu		
		int max_iter = 256;
		
		// Pętla po każdeym pikselu obrazu
		for(int X=0; X<WIDTH; X++)
		{
			for(int Y=0; Y<HEIGHT; Y++)
			{
				// Tworzenie pustej liczby zespolonej do przechowywania ostatniej lizby zespolonej
				ComplexNumber oldz = new ComplexNumber();
				
				
				ComplexNumber newz = new ComplexNumber(2.0*(X-WIDTH/2)/(WIDTH/2), 1.33*(Y-HEIGHT/2)/(HEIGHT/2) );
				

				int i;
				for(i=0;i<max_iter; i++)
				{
			
					oldz = newz;
									
					newz = newz.square();
					newz.add(constant);

					if(newz.mod() > 2)
						break;
				}
				
				float Brightness = i < max_iter ? 1f : 0;
				
				float Hue = (i%256)/255.0f;
				
				Color color = Color.getHSBColor(Hue, Saturation, Brightness);
				img.setRGB(X,Y,color.getRGB());
			}
		}
		
			JFrame f = new JFrame("Julia") {
				// rysujemy
				@Override
				public void paint(java.awt.Graphics g) {
					g.drawImage(img, 0, 0, null);
				}
			};
			// wlasnosci okna
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f.setSize((int)WIDTH,(int) HEIGHT);
			f.repaint();
			f.setVisible(true);
		
	}
}