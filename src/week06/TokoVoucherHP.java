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

private static void tampilkanMenuUtama() {
    System.out.println("\n-----------Menu Toko Voucher & HP-----------");
    System.out.println("1. Pesan Barang");
    System.out.println("2. Lihat Pesanan");
    System.out.println("3. Barang Baru");
    System.out.print("Pilihan : ");
}

private static void pesanBarang() {
    System.out.println("\n-----------Menu Toko Voucher & HP-----------");
    System.out.println("1. Pesan Barang");
    System.out.println("2. Lihat Pesanan");
    System.out.println("3. Barang Baru");
    System.out.print("Pilihan : 1\n");
    System.out.println("Daftar Barang Toko Voucher & HP");
    System.out.println("1. Handphone");
    System.out.println("2. Voucher");
    System.out.print("Pilihan : ");
    
    int jenis = scanner.nextInt();
    scanner.nextLine();

    tampilkanDaftarBarang(jenis);

    System.out.print("\nID\t: ");
    int idBarang = scanner.nextInt();
    scanner.nextLine();

    Barang barangDipilih = cariBarang(idBarang);
    
    if (barangDipilih == null) {
        System.out.println("Barang tidak tersedia");
        return;
    }

    System.out.println("Nama\t: " + barangDipilih.getNama());
    System.out.println("Stock\t: " + barangDipilih.getStok());
    System.out.println("Harga\t: " + (int)barangDipilih.getHarga());
    System.out.println("------------------------------------------");
    System.out.print("Ketik 0 untuk batal\nPesan Barang (ID) : ");
    
    int konfirmasi = scanner.nextInt();
    scanner.nextLine();

    if (konfirmasi == 0) {
        System.out.println("Pemesanan dibatalkan");
        return;
    }

    System.out.print("Masukkan Jumlah : ");
    int jumlah = scanner.nextInt();
    scanner.nextLine();

    if (jumlah > barangDipilih.getStok()) {
        System.out.println("Stok tidak mencukupi jumlah pesanan");
        return;
    }

    if (jumlah <= 0) {
        System.out.println("Jumlah tidak mencukupi");
        return;
    }

    // Kurangi stok
    barangDipilih.minusStok(jumlah);

    // Hitung total
    double hargaSatuan = 0;
    if (barangDipilih instanceof Voucher) {
        Voucher v = (Voucher) barangDipilih;
        hargaSatuan = v.getHargaJual();
    } else {
        hargaSatuan = barangDipilih.getHarga();
    }
    
    double total = jumlah * hargaSatuan;

    // Buat pesanan
    String orderId;
    if (barangDipilih instanceof Handphone) {
        orderId = "OH" + (Order.total + 1);
    } else {
        orderId = "OV" + (Order.total + 1);
    }

    if (barangDipilih instanceof Handphone) {
        daftarPesanan.add(new Order(orderId, null, (Handphone) barangDipilih, jumlah));
    } else {
        daftarPesanan.add(new Order(orderId, (Voucher) barangDipilih, null, jumlah));
    }

    System.out.println("\n1 @ " + barangDipilih.getNama() + " " + (int)hargaSatuan + " dengan harga " + (int)hargaSatuan);
    System.out.println("Masukkan jumlah uang : " + (int)total);
    System.out.println("Jumlah uang tidak mencukupi");
    System.out.println("Berhasil dipesan");
}

private static void lihatPesanan() {
    if (daftarPesanan.isEmpty()) {
        System.out.println("\nBelum ada pesanan");
        return;
    }

    System.out.println("\n-----------Menu Toko Voucher & HP-----------");
    System.out.println("1. Pesan Barang");
    System.out.println("2. Lihat Pesanan");
    System.out.println("3. Barang Baru");
    System.out.println("Pilihan : 2");
    System.out.println("Daftar Pesanan Toko Multiguna");

    for (Order order : daftarPesanan) {
        System.out.println("ID\t: " + order.getId());
        
        if (order.getHandphone() != null) {
            Handphone hp = order.getHandphone();
            System.out.println("Nama\t: " + hp.getNama());
            System.out.println("Jumlah\t: " + order.getJumlah());
            System.out.println("Total\t: " + (int)(hp.getHarga() * order.getJumlah()));
        } else if (order.getVoucher() != null) {
            Voucher v = order.getVoucher();
            System.out.println("Nama\t: " + v.getNama());
            System.out.println("Jumlah\t: " + order.getJumlah());
            System.out.println("Total\t: " + (int)(v.getHargaJual() * order.getJumlah()));
        }
        System.out.println("------------------------------------------");
    }
}

private static void barangBaru() {
    System.out.println("\n-----------Menu Toko Voucher & HP-----------");
    System.out.println("1. Pesan Barang");
    System.out.println("2. Lihat Pesanan");
    System.out.println("3. Barang Baru");
    System.out.print("Pilihan : 3\n");
    System.out.print("Voucher / Handphone (V/H): ");
    
    String jenis = scanner.nextLine();

    System.out.print("Nama : ");
    String nama = scanner.nextLine();

    System.out.print("Harga : ");
    double harga = scanner.nextDouble();

    System.out.print("Stok : ");
    int stok = scanner.nextInt();
    scanner.nextLine();

    if (jenis.equalsIgnoreCase("V")) {
        System.out.print("PPN : ");
        double pajak = scanner.nextDouble();
        scanner.nextLine();

        int id = daftarBarang.size() + 1;
        daftarBarang.add(new Voucher(id, nama, harga, stok, pajak));
        System.out.println("Voucher telah berhasil diinput");
    } else if (jenis.equalsIgnoreCase("H")) {
        System.out.print("Warna : ");
        String warna = scanner.nextLine();

        int id = daftarBarang.size() + 1;
        daftarBarang.add(new Handphone(id, nama, harga, stok, warna));
        System.out.println("Handphone telah berhasil diinput");
    } else {
        System.out.println("Pemilihan barang tidak sesuai");
    }
}

private static void tampilkanDaftarBarang(int jenis) {
    System.out.println();
    for (Barang barang : daftarBarang) {
        if (jenis == 1 && barang instanceof Handphone) {
            Handphone hp = (Handphone) barang;
            System.out.println("ID\t: " + hp.getId());
            System.out.println("Nama\t: " + hp.getNama());
            System.out.println("Stock\t: " + hp.getStok());
            System.out.println("Harga\t: " + (int)hp.getHarga());
            System.out.println();
        } else if (jenis == 2 && barang instanceof Voucher) {
            Voucher v = (Voucher) barang;
            System.out.println("ID\t: " + v.getId());
            System.out.println("Nama\t: " + v.getNama());
            System.out.println("Nominal\t: " + (int)v.getHarga());
            System.out.println("Stock\t: " + v.getStok());
            System.out.println("Harga\t: " + (int)v.getHargaJual());
            System.out.println();
        }
    }
}

private static Barang cariBarang(int id) {
    for (Barang barang : daftarBarang) {
        if (barang.getId() == id) {
            return barang;
        }
    }
    return null;
}
}