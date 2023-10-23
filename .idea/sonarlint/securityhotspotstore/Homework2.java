public class Homework {

    public interface Account {
        double getAmount();
        void put(double amount);
        void take(double amount);
    }

    public abstract class AbstractAccount implements Account {
        protected double balance;

        public AbstractAccount(double initialBalance) {
            this.balance = initialBalance;
        }

        @Override
        public double getAmount() {
            return balance;
        }

        @Override
        public void put(double amount) {
            if (amount < 0) {
                throw new IllegalArgumentException("Сумма пополнения должна быть положительной");
            }
            balance += amount;
        }

        @Override
        public void take(double amount) {
            if (amount < 0 || amount > balance) {
                throw new IllegalArgumentException("Сумма снятия должна быть положительной и не превышать текущий баланс");
            }
            balance -= amount;
        }
    }

    public class FixedAmountAccount extends AbstractAccount {

        public FixedAmountAccount(double initialBalance) {
            super(initialBalance);
        }

        @Override
        public void put(double amount) {
            // Ничего не делаем
        }

        @Override
        public void take(double amount) {
            // Ничего не делаем
        }
    }
}
