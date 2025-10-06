package week06;

public class TokoVoucherHP {

	import java.util.ArrayList;
	import java.util.Scanner;

	class Barang {
	    private int id;
	    private double harga;
	    private String nama;
	    private int stok;

	    public Barang(int id, String nama, double harga, int stok) {
	        this.id = id;
	        this.nama = nama;
	        this.harga = harga;
	        this.stok = stok;
	    }

	    public int getId() {
	        return id;
	    }

	    public String getNama() {
	        return nama;
	    }

	    public double getHarga() {
	        return harga;
	    }

	    public int getStok() {
	        return stok;
	    }

	    public void minusStok(int jumlah) {
	        this.stok -= jumlah;
	    }
	}
}

//Class Handphone
class Handphone extends Barang {
private String warna;
public static int total = 0;

public Handphone(int id, String nama, double harga, int stok, String warna) {
   super(id, nama, harga, stok);
   this.warna = warna;
   total++;
}

public String getWarna() {
   return warna;
}
}

//Class Voucher
class Voucher extends Barang {
private double pajak;
public static int total = 0;

public Voucher(int id, String nama, double harga, int stok, double pajak) {
   super(id, nama, harga, stok);
   this.pajak = pajak;
   total++;
}

public double getPajak() {
   return pajak;
}

public double getHargaJual() {
   return getHarga() + (getHarga() * pajak / 100);
}
}

//Class Order
class Order {
private String id;
private Voucher voucher;
private Handphone handphone;
private int jumlah;
public static int total = 0;

public Order(String id, Voucher voucher, Handphone handphone, int jumlah) {
   this.id = id;
   this.voucher = voucher;
   this.handphone = handphone;
   this.jumlah = jumlah;
   total++;
}

public String getId() {
   return id;
}

public Voucher getVoucher() {
   return voucher;
}

public Handphone getHandphone() {
   return handphone;
}

public int getJumlah() {
   return jumlah;
}
}

//Main Class
public class TokoVoucherHP {
private static ArrayList<Barang> daftarBarang = new ArrayList<>();
private static ArrayList<Order> daftarPesanan = new ArrayList<>();
private static Scanner scanner = new Scanner(System.in);

public static void main(String[] args) {
   // Inisialisasi data barang
   daftarBarang.add(new Handphone(1, "Samsung S9+ Hitam", 14499000, 10, "Hitam"));
   daftarBarang.add(new Handphone(2, "iPhone X Hitam", 18999000, 10, "Hitam"));
   daftarBarang.add(new Voucher(1, "Google Play", 20000, 100, 0.1));
   daftarBarang.add(new Handphone(3, "Oppo F9", 3999000, 10, "Hitam"));

   boolean running = true;
   while (running) {
       tampilkanMenuUtama();
       int pilihan = scanner.nextInt();
       scanner.nextLine(); // consume newline

       switch (pilihan) {
           case 1:
               pesanBarang();
               break;
           case 2:
               lihatPesanan();
               break;
           case 3:
               barangBaru();
               break;
           case 0:
               running = false;
               System.out.println("Terima kasih!");
               break;
           default:
               System.out.println("Pilihan tidak valid!");
       }
   }
}

