/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kurtalan;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Ali Ahmet
 */
public class Kurtalan {

    static int yukCounter = 1;
    static int anaCounter = 1;
    static int hizliCounter = 1;
    
    
    // 6 4 12 
    // 2 3 4
    
    static int seferBasSaat=10;
    static int seferBasDakika=0;
    static void timeCalc(int dakika) {
    
    	seferBasDakika+=dakika;
    	if(seferBasDakika>59) {
    		int bolum=seferBasDakika/60;
    		seferBasDakika=seferBasDakika%60;
    		seferBasSaat+=bolum;
    	}
		if(seferBasSaat==24) {
			seferBasSaat=0;
		}
    	if(seferBasSaat>23) {
    		seferBasSaat-=24;
    	}
    }
    public static String hizliTren(int gun, float ortalama_hiz, Durak[] rota, int kacinci) {
        int sayac=0;
        int sayac2=0;
        int j = 0;
        boolean flag = true;
        float dakika_counter = -120;
        //int sayac=rota1[j].birSonrakiDuragaUzaklik;
        //System.out.println(rota[j].durakIsmi);
        sayac = rota[j].birSonrakiDuragaUzaklik;
        sayac2=sayac-sayac2;
        
        
        //burayı dinamik hale getir
        dakika_counter = dakika_counter + (120 * kacinci);
        //float ortalama_hiz = 105;
        System.out.print("Durak İsmi"+"\t");
        System.out.print("Kaçıncı Km"+"\t");
        System.out.print("Ortalama Hız"+"\t");
        System.out.print("Varış Saati"+"\t");
        System.out.print("Çıkış Saati"+"\t");
        System.out.println("Bekleme Süresi"+"\t");
        
        System.out.print(rota[j].durakIsmi+"\t\t");
        System.out.print(0+"\t\t");
        System.out.print(ortalama_hiz+"\t\t");
        System.out.print("--"+":"+"--"+"\t\t");
        System.out.print(seferBasSaat+":"+seferBasDakika+"\t\t");
        System.out.println("----"+"\t\t");
        
        j++;
        
        int sefer = 0;
        int mola = 20;
        int seferBakim = 120;
        int genelBakim = 12 * 60;
        float gunLimit =0;
        int total_mola = 0;
        int total_bakim = 0;
        
        //int gun  = 2;
        //int[][] tablo = new int[3][];
        float tahminiBirSefer = (450/ortalama_hiz)*60 + 120;
        while ( dakika_counter < gun * 24 * 60 ) {
            System.out.print(rota[j].durakIsmi+"\t\t");
            System.out.print(sayac+"\t\t");
            System.out.print(ortalama_hiz+"\t\t");
            timeCalc(Math.round((sayac2/ortalama_hiz)*60));
            System.out.print(seferBasSaat+":"+seferBasDakika+"\t\t");
            timeCalc(mola);
            System.out.print(seferBasSaat+":"+seferBasDakika+"\t\t");
            System.out.println(mola+"\t\t");
            sayac2=sayac;
            //System.out.println(sayac);
            //System.out.println(sefer);
            //System.out.println(rota[j].durakIsmi);
            //System.out.println("Rota1'de Alınan yol: " + sayac);
            if (flag == true) {

                if (sayac + rota[j].birSonrakiDuragaUzaklik >= 6000) {
                    sayac = 0;
                    dakika_counter += genelBakim;
                    total_bakim += genelBakim;
                    //System.out.println("genel bakım yapılıyor");

                    continue;
                } else {
                    sayac += rota[j].birSonrakiDuragaUzaklik;
                    //System.out.println((rota[j].birSonrakiDuragaUzaklik/ortalama_hiz)*60);
                    dakika_counter += (rota[j].birSonrakiDuragaUzaklik / ortalama_hiz) * 60;
                    dakika_counter += mola;
                    total_mola += mola;
                    j++;
                }

            }
            if (flag == false) {
                if (sayac + rota[j].birOncekiDuragaUzaklik >= 6000) {
                    sayac = 0;
                    dakika_counter += genelBakim;
                    total_bakim += genelBakim;
                    //System.out.println("genel bakım yapılıyor");
                    
                    continue;
                } else {
                    sayac += rota[j].birOncekiDuragaUzaklik;
                    dakika_counter += (rota[j].birOncekiDuragaUzaklik / ortalama_hiz) * 60;
                    dakika_counter += mola;
                    total_mola += mola;
                    j--;
                }
            }
            if (j == 0) {
                //System.out.println("seferin biri bitti - başa geldi");
                sefer += 1;
                dakika_counter += seferBakim;
                total_bakim += seferBakim;
                gunLimit = dakika_counter;
                flag = true;
            }
            if (j == 6) {
                //System.out.println("seferin biri bitti - sona geldi");
                sefer += 1;
                dakika_counter += seferBakim;
                total_bakim += seferBakim;
                gunLimit = dakika_counter;
                flag = false;
            }
            sayac2=sayac-sayac2;
            //seferBasSaat=10;
            //seferBasDakika=0;
        }
        //System.out.println(beklemeArr.size());
        //System.out.println(yollArr.size());
        //System.out.println("total bakım: " + total_bakim/60.0);
        //System.out.println("total mola: " + total_mola/60.0);
        //System.out.println("total saat:" + dakika_counter /60.0);
        System.out.println("Sefer Sayısı:" + sefer);
        return Integer.toString(sefer) + " " + Integer.toString(total_bakim) + " " + Integer.toString(total_mola); 

    }

    public static String yukTreni(int gun, float ortalama_hiz, Durak[] rota, int kacinci) {
        int sayac=0;
        int sayac2=0;
        int j = 0;
        boolean flag = true;
        float dakika_counter = -180;
        //int sayac=rota1[j].birSonrakiDuragaUzaklik;
        //System.out.println(rota[j].durakIsmi);
        sayac = rota[j].birSonrakiDuragaUzaklik;
        sayac2=sayac-sayac2;
        
        
        //burayı dinamik hale getir
        dakika_counter = dakika_counter + (120 * kacinci);
        //float ortalama_hiz = 105;
        System.out.print("Durak İsmi"+"\t");
        System.out.print("Kaçıncı Km"+"\t");
        System.out.print("Ortalama Hız"+"\t");
        System.out.print("Varış Saati"+"\t");
        System.out.print("Çıkış Saati"+"\t");
        System.out.println("Bekleme Süresi"+"\t");
        
        System.out.print(rota[j].durakIsmi+"\t\t");
        System.out.print(0+"\t\t");
        System.out.print(ortalama_hiz+"\t\t");
        System.out.print("--"+":"+"--"+"\t\t");
        System.out.print(seferBasSaat+":"+seferBasDakika+"\t\t");
        System.out.println("----"+"\t\t");
        
        j++;
        
        int sefer = 0;
        int mola = 0;
        int seferBakim = 180;
        int genelBakim = 36 * 60;
        float gunLimit =0;
        int total_mola = 0;
        int total_bakim = 0;
        
        //int gun  = 2;
        //int[][] tablo = new int[3][];
        float tahminiBirSefer = (518/ortalama_hiz)*60;
        while ( dakika_counter < gun * 24 * 60 ) {
            System.out.print(rota[j].durakIsmi+"\t\t");
            System.out.print(sayac+"\t\t");
            System.out.print(ortalama_hiz+"\t\t");
            timeCalc(Math.round((sayac2/ortalama_hiz)*60));
            System.out.print(seferBasSaat+":"+seferBasDakika+"\t\t");
            timeCalc(mola);
            System.out.print(seferBasSaat+":"+seferBasDakika+"\t\t");
            System.out.println(mola+"\t\t");
            sayac2=sayac;
            //System.out.println(sayac);
            //System.out.println(sefer);
            //System.out.println(rota[j].durakIsmi);
            //System.out.println("Rota1'de Alınan yol: " + sayac);
            if (flag == true) {

                if (sayac + rota[j].birSonrakiDuragaUzaklik >= 3000) {
                    sayac = 0;
                    dakika_counter += genelBakim;
                    total_bakim += genelBakim;
                    //System.out.println("genel bakım yapılıyor");

                    continue;
                } else {
                    sayac += rota[j].birSonrakiDuragaUzaklik;
                    //System.out.println((rota[j].birSonrakiDuragaUzaklik/ortalama_hiz)*60);
                    dakika_counter += (rota[j].birSonrakiDuragaUzaklik / ortalama_hiz) * 60;
                    dakika_counter += mola;
                    total_mola += mola;
                    j++;
                }

            }
            if (flag == false) {
                if (sayac + rota[j].birOncekiDuragaUzaklik >= 3000) {
                    sayac = 0;
                    dakika_counter += genelBakim;
                    total_bakim += genelBakim;
                    //System.out.println("genel bakım yapılıyor");
                    
                    continue;
                } else {
                    sayac += rota[j].birOncekiDuragaUzaklik;
                    dakika_counter += (rota[j].birOncekiDuragaUzaklik / ortalama_hiz) * 60;
                    dakika_counter += mola;
                    total_mola += mola;
                    j--;
                }
            }
            if (j == 0) {
                //System.out.println("seferin biri bitti - başa geldi");
                sefer += 1;
                dakika_counter += seferBakim;
                total_bakim += seferBakim;
                gunLimit = dakika_counter;
                flag = true;
            }
            if (j == 6) {
                //System.out.println("seferin biri bitti - sona geldi");
                sefer += 1;
                dakika_counter += seferBakim;
                total_bakim += seferBakim;
                gunLimit = dakika_counter;
                flag = false;
            }
            sayac2=sayac-sayac2;
            //seferBasSaat=10;
            //seferBasDakika=0;
        }
        //System.out.println(beklemeArr.size());
        //System.out.println(yollArr.size());
        //System.out.println("total bakım: " + total_bakim/60.0);
        //System.out.println("total mola: " + total_mola/60.0);
        //System.out.println("total saat:" + dakika_counter /60.0);
        System.out.println("Sefer Sayısı:" + sefer);
        return Integer.toString(sefer) + " " + Integer.toString(total_bakim) + " " + Integer.toString(total_mola);
    }
    
        public static String anahatTreni(int gun, float ortalama_hiz, Durak[] rota, int kacinci) {
        int sayac=0;
        int sayac2=0;
        int j = 0;
        boolean flag = true;
        float dakika_counter = -240;
        //int sayac=rota1[j].birSonrakiDuragaUzaklik;
        //System.out.println(rota[j].durakIsmi);
        sayac = rota[j].birSonrakiDuragaUzaklik;
        sayac2=sayac-sayac2;
        
        
        //burayı dinamik hale getir
        dakika_counter = dakika_counter + (120 * kacinci);
        //float ortalama_hiz = 105;
        System.out.print("Durak İsmi"+"\t");
        System.out.print("Kaçıncı Km"+"\t");
        System.out.print("Ortalama Hız"+"\t");
        System.out.print("Varış Saati"+"\t");
        System.out.print("Çıkış Saati"+"\t");
        System.out.println("Bekleme Süresi"+"\t");
        
        System.out.print(rota[j].durakIsmi+"\t\t");
        System.out.print(0+"\t\t");
        System.out.print(ortalama_hiz+"\t\t");
        System.out.print("--"+":"+"--"+"\t\t");
        System.out.print(seferBasSaat+":"+seferBasDakika+"\t\t");
        System.out.println("----"+"\t\t");
        
        j++;
        
        int sefer = 0;
        int mola = 25;
        int seferBakim = 240;
        int genelBakim = 24 * 60;
        float gunLimit =0;
        int total_mola = 0;
        int total_bakim = 0;
        
        //int gun  = 2;
        //int[][] tablo = new int[3][];
        float tahminiBirSefer = (450/ortalama_hiz)*60 + 150;
        while ( dakika_counter < gun * 24 * 60 ) {
            System.out.print(rota[j].durakIsmi+"\t\t");
            System.out.print(sayac+"\t\t");
            System.out.print(ortalama_hiz+"\t\t");
            timeCalc(Math.round((sayac2/ortalama_hiz)*60));
            System.out.print(seferBasSaat+":"+seferBasDakika+"\t\t");
            timeCalc(mola);
            System.out.print(seferBasSaat+":"+seferBasDakika+"\t\t");
            System.out.println(mola+"\t\t");
            sayac2=sayac;
            //System.out.println(sayac);
            //System.out.println(sefer);
            //System.out.println(rota[j].durakIsmi);
            //System.out.println("Rota1'de Alınan yol: " + sayac);
            if (flag == true) {

                if (sayac + rota[j].birSonrakiDuragaUzaklik >= 6000) {
                    sayac = 0;
                    dakika_counter += genelBakim;
                    total_bakim += genelBakim;
                    //System.out.println("genel bakım yapılıyor");

                    continue;
                } else {
                    sayac += rota[j].birSonrakiDuragaUzaklik;
                    //System.out.println((rota[j].birSonrakiDuragaUzaklik/ortalama_hiz)*60);
                    dakika_counter += (rota[j].birSonrakiDuragaUzaklik / ortalama_hiz) * 60;
                    dakika_counter += mola;
                    total_mola += mola;
                    j++;
                }

            }
            if (flag == false) {
                if (sayac + rota[j].birOncekiDuragaUzaklik >= 6000) {
                    sayac = 0;
                    dakika_counter += genelBakim;
                    total_bakim += genelBakim;
                    //System.out.println("genel bakım yapılıyor");
                    
                    continue;
                } else {
                    sayac += rota[j].birOncekiDuragaUzaklik;
                    dakika_counter += (rota[j].birOncekiDuragaUzaklik / ortalama_hiz) * 60;
                    dakika_counter += mola;
                    total_mola += mola;
                    j--;
                }
            }
            if (j == 0) {
                //System.out.println("seferin biri bitti - başa geldi");
                sefer += 1;
                dakika_counter += seferBakim;
                total_bakim += seferBakim;
                gunLimit = dakika_counter;
                flag = true;
            }
            if (j == 6) {
                //System.out.println("seferin biri bitti - sona geldi");
                sefer += 1;
                dakika_counter += seferBakim;
                total_bakim += seferBakim;
                gunLimit = dakika_counter;
                flag = false;
            }
            sayac2=sayac-sayac2;
            //seferBasSaat=10;
            //seferBasDakika=0;
        }
        //System.out.println(beklemeArr.size());
        //System.out.println(yollArr.size());
        //System.out.println("total bakım: " + total_bakim/60.0);
        //System.out.println("total mola: " + total_mola/60.0);
        //System.out.println("total saat:" + dakika_counter /60.0);
        System.out.println("Sefer Sayısı:" + sefer);
        return Integer.toString(sefer) + " " + Integer.toString(total_bakim) + " " + Integer.toString(total_mola);

    }

    public static void main(String[] args) throws IOException {
        //-------ROTA 1--------
        Durak[] rota1 = new Durak[7];
        Durak A = new Durak(false, false, "A", "", 100, 0, 0);
        rota1[0] = A;
        Durak B = new Durak(false, false, "B", "", 75, 100, 0);
        rota1[1] = B;
        Durak C = new Durak(false, false, "C", "", 100, 75, 0);
        rota1[2] = C;
        Durak D = new Durak(false, false, "D", "", 75, 100, 0);
        rota1[3] = D;
        Durak E = new Durak(false, false, "E", "", 75, 75, 0);
        rota1[4] = E;
        Durak F = new Durak(false, false, "F", "", 25, 75, 0);
        rota1[5] = F;
        Durak O = new Durak(false, false, "O", "", 0, 25, 0);
        rota1[6] = O;

        //-------ROTA 2--------
        Durak[] rota2 = new Durak[7];
        Durak G = new Durak(false, false, "G", "", 77, 100, 0);
        rota2[0] = G;
        Durak H = new Durak(false, false, "H", "", 82, 77, 0);
        rota2[1] = H;
        Durak I = new Durak(false, false, "I", "", 50, 82, 0);
        rota2[2] = I;
        Durak F_2 = new Durak(false, false, "F_2", "", 97, 50, 0);
        rota2[3] = F_2;
        Durak J = new Durak(false, false, "J", "", 100, 97, 0);
        rota2[4] = J;
        Durak K = new Durak(false, false, "K", "", 112, 100, 0);
        rota2[5] = F;
        Durak L = new Durak(false, false, "L", "", 0, 112, 0);
        rota2[6] = L;

        //-------ROTA 3--------
        Durak[] rota3 = new Durak[7];
        Durak N = new Durak(false, false, "N", "", 100, 0, 0);
        rota3[0] = N;
        Durak K_2 = new Durak(false, false, "K_2", "", 100, 100, 0);
        rota3[1] = K_2;
        Durak P = new Durak(false, false, "P", "", 75, 100, 0);
        rota3[2] = P;
        Durak R = new Durak(false, false, "R", "", 50, 75, 0);
        rota3[3] = R;
        Durak D_2 = new Durak(false, false, "D_2", "", 50, 50, 0);
        rota3[4] = D_2;
        Durak S = new Durak(false, false, "S", "", 75, 50, 0);
        rota3[5] = S;
        Durak P_2 = new Durak(false, false, "P_2", "", 0, 75, 0);
        rota3[6] = P_2;
        //-------rota1----
//        String sonuc;
//        FileWriter fw=new FileWriter("sonuc.txt");    
//        for (int i = 100; i < 199; i++) {
//            sonuc = hizliTren(30, i, rota1, 1);
//            fw.write(sonuc + "\n");
//        }
//        fw.close();
        
        int gun_son;
        Scanner s4 = new Scanner(System.in);
        System.out.print("İstediğiniz gün değerini girin: ");
        gun_son = s4.nextInt();
        
        int htsayisi;
        Scanner s1 = new Scanner(System.in);
        System.out.print("Hızlı tren sayısını giriniz: ");
        htsayisi = s1.nextInt();
        
        //for (int i = htsayisi; htsayisi > 0; i--) {
        //hizliTren(1, 198, rota1, htsayisi);
        //hizliTren(1, 198, rota1, htsayisi-1);
        while(htsayisi > 0){
            System.out.println("TREN HT-" + htsayisi);
            hizliTren(gun_son, 198, rota1, htsayisi);
            htsayisi-=1;
        }
        
        int ytsayisi;
        Scanner s2 = new Scanner(System.in);
        System.out.print("Yük treni sayısını giriniz: ");
        ytsayisi = s2.nextInt();
        
        //for (int i = htsayisi; htsayisi > 0; i--) {
        //hizliTren(1, 198, rota1, htsayisi);
        //hizliTren(1, 198, rota1, htsayisi-1);
        while(ytsayisi > 0){
            System.out.println("TREN YT-" + ytsayisi);
            yukTreni(gun_son, 55, rota2, ytsayisi);
            ytsayisi-=1;
        }
        
        int atsayisi;
        Scanner s3 = new Scanner(System.in);
        System.out.print("Anahat treni sayısını giriniz: ");
        atsayisi = s3.nextInt();
        
        //for (int i = htsayisi; htsayisi > 0; i--) {
        //hizliTren(1, 198, rota1, htsayisi);
        //hizliTren(1, 198, rota1, htsayisi-1);
        while(atsayisi > 0){
            System.out.println("TREN AT-" + atsayisi);
            anahatTreni(gun_son, 60, rota3, atsayisi);
            atsayisi-=1;
        }
        
    }

    public static class Durak {

        boolean gidisStatus;
        boolean donusStatus;
        String durakIsmi;
        String trenTipi;
        int birSonrakiDuragaUzaklik;
        int birOncekiDuragaUzaklik;
        int birSonrakiTrenIcinBeklemeSuresi;//kaç dakika sonra durak free olacak // Ali ahmet sleep fonksiyonu

        public Durak(boolean gidisStatus, boolean donusStatus, String durakIsmi, String trenTipi,
                int birSonrakiDuragaUzaklik, int birOncekiDuragaUzaklik, int birSonrakiTrenIcinBeklemeSuresi) {
            this.gidisStatus = gidisStatus;
            this.donusStatus = donusStatus;
            this.durakIsmi = durakIsmi;
            this.trenTipi = trenTipi;
            this.birSonrakiDuragaUzaklik = birSonrakiDuragaUzaklik;
            this.birOncekiDuragaUzaklik = birOncekiDuragaUzaklik;
            this.birSonrakiTrenIcinBeklemeSuresi = birSonrakiTrenIcinBeklemeSuresi;
        }

        public Durak() {

        }
    }

}
