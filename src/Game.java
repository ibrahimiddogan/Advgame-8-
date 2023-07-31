import java.util.Random;
import java.util.Scanner;
public class Game {
    private Random random;
    private Player player;
    private Enemy currentEnemy;
    private Scanner scanner;

    public Game() {
        random = new Random();
        player = new Player();
        scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("Macera Oyununa Hoş Geldiniz!");
        System.out.println("Oyunu bitirmek için tüm ödülleri toplayıp \"Güvenli Eve\" dönmelisiniz.");

        while (!player.hasWon() && player.getHealth() > 0) {
            explore();
        }

        if (player.hasWon()) {
            System.out.println("Tebrikler, tüm ödülleri toplayarak oyunu kazandınız!");
        } else {
            System.out.println("Oyunu kaybettiniz. Sağlık puanınız sıfırlandı.");
        }
    }

    private void explore() {
        String[] regions = {"Mağara", "Orman", "Nehir", "Maden"};
        String currentRegion = regions[random.nextInt(regions.length)];

        if (!hasVisited(currentRegion)) {
            System.out.println("Yeni bölge keşfedildi: " + currentRegion);
            visitRegion(currentRegion);
        } else {
            System.out.println("Bu bölgeye tekrar giriş yapılamaz: " + currentRegion);
        }
    }

    private boolean hasVisited(String region) {
        switch (region) {
            case "Mağara":
                return player.getFood() > 0;
            case "Orman":
                return player.getFirewood() > 0;
            case "Nehir":
                return player.getWater() > 0;
            case "Maden":
                return player.getMoney() > 0 || player.getWeapon() > 0 || player.getArmor() > 0;
            default:
                return false;
        }
    }

    private void visitRegion(String region) {
        switch (region) {
            case "Mağara":
                System.out.println("Mağara bölgesine girdiniz. Yemek ödülünü kazandınız.");
                player.setFood(1);
                break;
            case "Orman":
                System.out.println("Orman bölgesine girdiniz. Odun ödülünü kazandınız.");
                player.setFirewood(1);
                break;
            case "Nehir":
                System.out.println("Nehir bölgesine girdiniz. Su ödülünü kazandınız.");
                player.setWater(1);
                break;
            case "Maden":
                System.out.println("Maden bölgesine girdiniz. Düşmanlarla savaş başladı!");
                battle();
                break;
        }
    }

    private void battle() {
        currentEnemy = new Enemy("Yılan", random.nextInt(4) + 3, 12);

        while (player.getHealth() > 0 && currentEnemy.isAlive()) {
            System.out.println("-------------------------");
            System.out.println("Sağlık: " + player.getHealth());
            System.out.println("Düşman: " + currentEnemy.getHealth());
            System.out.println("-------------------------");
            System.out.println("Hamle yapmak için 1'e basın.");

            scanner.nextLine();

            if (random.nextBoolean()) {
                System.out.println("Oyuncu ilk hamleyi yapıyor.");
                currentEnemy.takeDamage(player.getWeapon());
            } else {
                System.out.println("Düşman ilk hamleyi yapıyor.");
                player.setHealth(player.getHealth() - currentEnemy.getDamage());
            }
        }

        if (player.getHealth() > 0) {
            System.out.println("Düşmanı yendiniz! Rastgele bir ödül kazandınız.");
            getRewards();
        } else {
            System.out.println("Oyuncu öldü. Oyunu kaybettiniz.");
            player.setHealth(0);
        }
    }

    private void getRewards() {
        int rewardRoll = random.nextInt(100) + 1;

        if (rewardRoll <= 15) {
            System.out.println("Silah kazandınız!");
            player.setWeapon(random.nextInt(4) + 1);
        } else if (rewardRoll <= 35) {
            System.out.println("Zırh kazandınız!");
            player.setArmor(random.nextInt(4) + 1);
        } else if (rewardRoll <= 60) {
            System.out.println("Para kazandınız!");
            int moneyAmount = 1;
            if (rewardRoll <= 40) {
                moneyAmount = 5;
            } else if (rewardRoll <= 55) {
                moneyAmount = 10;
            }
            player.setMoney(player.getMoney() + moneyAmount);
        } else {
            System.out.println("Hiçbir şey kazanamadınız.");
        }
    }
}
