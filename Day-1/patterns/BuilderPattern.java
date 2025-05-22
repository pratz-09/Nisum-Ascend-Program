package patterns;

class User {
    private User(Builder b) {
    }

    static class Builder {
        public Builder setName(String name) {
            return this;
        }

        public Builder setEmail(String email) {
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}

public class BuilderPattern {
    public static void main(String[] args) {
        System.out.println("User created");
    }
}