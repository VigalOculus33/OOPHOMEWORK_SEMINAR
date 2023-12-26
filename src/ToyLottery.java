import java.util.ArrayList;
import java.util.List;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ToyLottery {
    private List<Toy> toys = new ArrayList<>();
    private List<Toy> prizeToys = new ArrayList<>(); // Список призовых игрушек

    public void addToy(Toy toy) {
        toys.add(toy);
        log("Игрушка добавлена: " + toy);
    }

    public Toy drawToy() {
        // Кастомный алгоритм выбора игрушки
        int index = (int) (Math.random() * toys.size());
        return toys.get(index);
    }

    public void drawAndAddToPrizeList() {
        Toy prizeToy = drawToy();
        if (prizeToy != null) {
            prizeToys.add(prizeToy);
            log("Призовая игрушка добавлена: " + prizeToy);
        } else {
            log("Игрушки для розыгрыша не найдены!");
        }
    }

    public void giveOutPrize() {
        if (!prizeToys.isEmpty()) {
            Toy prizeToy = prizeToys.remove(0);
            prizeToy.setQuantity(prizeToy.getQuantity() - 1);
            log("Призовая игрушка выдана: " + prizeToy);
            saveToyToFile(prizeToy);
        } else {
            log("Нет призовых игрушек для выдачи!");
        }
    }

    private void saveToyToFile(Toy toy) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("prize_toys.txt", true))) {
            writer.write(toy.toString());
            writer.newLine();
        } catch (IOException e) {
            log("Произошла ошибка при записи игрушки в файл: " + e.getMessage());
        }
    }

    private void log(String message) {
        System.out.println("[LOG] " + message);
    }

    public void showAllToys() {
        toys.forEach(toy -> System.out.println(toy));
    }

    public static void main(String[] args) {
        ToyLottery lottery = new ToyLottery();
        lottery.addToy(new Toy(1, "Медведь Пидобир", 10, 30.0));
        lottery.addToy(new Toy(2, "Игрушечная пила", 15, 50.0));
        lottery.addToy(new Toy(2, "Спинер", 5, 5.0));

        // Показать все доступные игрушки
        lottery.showAllToys();

        // Проведите розыгрыш и выдайте призы
        lottery.drawAndAddToPrizeList();
        lottery.giveOutPrize();
    }
}

