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

