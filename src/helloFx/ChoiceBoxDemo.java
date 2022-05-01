package helloFx;

import javafx.application.Application;
import javafx.beans.value.*;
import javafx.collections.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ChoiceBoxDemo extends Application {
    public static void main(String[] args) throws Exception {
        launch(args);
    }

    public void start(final Stage stage) throws Exception {
        // create a backing database;
        final Database db = new Database();

        // provide some instructions.
        final Label instructions = new Label("Choose an opponent.");
        instructions.setMouseTransparent(true);

        // provide some choices based on the animals in the database;
        ObservableList<Choice> choices = FXCollections.observableArrayList();
        choices.add(new Choice(null, "No selection"));
        for (Database.Animal animal : db.findAllAnimals()) {
            choices.add(new Choice(animal.id, animal.name));
        }
        final ChoiceBox<Choice> chooser = new ChoiceBox<>(choices);
        chooser.getSelectionModel().select(0);

        // act on a choice.
        chooser.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Choice>() {
            @Override
            public void changed(ObservableValue<? extends Choice> observableValue, Choice oldChoice, Choice newChoice) {
                if (newChoice.id == null) {
                    instructions.setText("You have chosen cowardice.");
                } else {
                    // lookup info by id in the database.
                    Database.Animal opponent = db.findAnimal(newChoice.id);
                    StringBuilder weapons = new StringBuilder();
                    for (int weaponId : opponent.weaponIds) {
                        weapons.append("\n ").append(db.findWeapon(weaponId).name);
                    }

                    // format and display the lookup info.
                    instructions.setText(
                            "You chosen to face the " + opponent.name + ".\n\n" +
                                    "Beware of your opponents deadly attacks:" + weapons + "\n\n" +
                                    "Good Luck!");
                }
            }
        });

        // show the scene.
        final VBox layout = new VBox(20);
        layout.setStyle("-fx-background-color: cornsilk; -fx-padding: 10; -fx-font-size: 20;");
        layout.getChildren().addAll(instructions, chooser);
        Scene scene = new Scene(layout, 550, 350);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Helper class for mapping a choice displayable in a ChoiceBox to a backing id.
     */
    class Choice {
        Integer id;
        String displayString;

        Choice(Integer id) {
            this(id, null);
        }

        Choice(String displayString) {
            this(null, displayString);
        }

        Choice(Integer id, String displayString) {
            this.id = id;
            this.displayString = displayString;
        }

        @Override
        public String toString() {
            return displayString;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;
            Choice choice = (Choice) o;
            return displayString != null && displayString.equals(choice.displayString)
                    || id != null && id.equals(choice.id);
        }

        @Override
        public int hashCode() {
            int result = id != null ? id.hashCode() : 0;
            result = 31 * result + (displayString != null ? displayString.hashCode() : 0);
            return result;
        }
    }

    /** Quick and dirty in memory database */
    class Database {
        private Weapon[] weapons = {
                new Weapon(1, "Flaming Breath"), new Weapon(2, "Feathers of Doom"), new Weapon(3, "Sleeping Sickness"),
                new Weapon(4, "Drop from above"), new Weapon(5, "Laughter"), new Weapon(6, "Goodnight")
        };
        private Animal[] animals = {
                new Animal(1, "Burning Emu", 1, 2), new Animal(2, "Killer Koala", 3, 4),
                new Animal(3, "Outrageous Orangutan", 5)
        };

        Animal findAnimal(int id) {
            for (Animal animal : animals)
                if (animal.id == id)
                    return animal;
            return null;
        }

        Animal[] findAllAnimals() {
            return animals;
        }

        Weapon findWeapon(int id) {
            for (Weapon weapon : weapons)
                if (weapon.id == id)
                    return weapon;
            return null;
        }

        class Animal {
            int id;
            String name;
            int[] weaponIds;

            Animal(int id, String name, int... weaponIds) {
                this.id = id;
                this.name = name;
                this.weaponIds = weaponIds;
            }

            @Override
            public String toString() {
                return name;
            }
        }

        class Weapon {
            int id;
            String name;

            Weapon(int id, String name) {
                this.id = id;
                this.name = name;
            }

            @Override
            public String toString() {
                return name;
            }
        }
    }
}