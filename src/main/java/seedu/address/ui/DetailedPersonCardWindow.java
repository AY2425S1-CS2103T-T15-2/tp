package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import seedu.address.model.person.Person;

/**
 * A UI component that displays detailed information of a {@code Person}.
 */
public class DetailedPersonCardWindow extends UiPart<Stage> {

    private static Person personToShow;
    private static final String FXML = "DetailedPersonCardWindow.fxml";

    @FXML
    private VBox cardPane;

    @FXML
    private TextArea detailedPerson;

    @FXML
    private FlowPane tags;

    public DetailedPersonCardWindow(Stage root) {
        super(FXML, root);
    }

    public DetailedPersonCardWindow() {
        this(new Stage());
    }

    @FXML
    private void initialize() {
        assert personToShow != null;
        StringBuilder detailedPersonText = new StringBuilder();
        detailedPersonText.append("Name: ").append(personToShow.getName().fullName).append("\n\n");
        detailedPersonText.append("Phone: ").append(personToShow.getPhone().value).append("\n\n");
        detailedPersonText.append("Email: ").append(personToShow.getEmail().value).append("\n\n");
        detailedPersonText.append("Address: ").append(personToShow.getAddress().value).append("\n\n");
        detailedPersonText.append("ClassId: ").append(personToShow.getClassId().value).append("\n\n");
        detailedPersonText.append("Fees: ").append(personToShow.getFees().value).append("\n\n");
        String monthsPaid = personToShow.getMonthsPaid().stream()
                .map(monthPaid -> monthPaid.monthPaidValue)
                .reduce((curr, next) -> curr + " " + next)
                .orElse("(empty)");
        detailedPersonText.append("Months Paid: ").append(monthsPaid).append("\n\n");
        detailedPersonText.append("Tags: ").append(personToShow.getTags().toString()).append("\n");
        detailedPerson.setText(detailedPersonText.toString());


    }

    public static void setPerson(Person person) {
        personToShow = person;
    }

    /**
     * Shows the Detailed Person Card window.
     */
    public void show() {
        getRoot().setTitle(personToShow.getName().fullName);
        getRoot().show();
        getRoot().centerOnScreen();

    }

    public boolean isShowing() {
        return getRoot().isShowing();
    }

    public void close() {
        getRoot().close();
    }

    public void focus() {
        getRoot().requestFocus();
    }



}
