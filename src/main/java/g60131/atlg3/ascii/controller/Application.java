package g60131.atlg3.ascii.controller;
import g60131.atlg3.ascii.model.AsciiPaint;
import g60131.atlg3.ascii.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Application {
    private AsciiPaint asciiPaint;
    private boolean quit = false;
    private View view = new View();
    public static void main(String[] args) {
        Application application = new Application();
        application.start();
    }

    /**
     * start the main method
     */
    public void start() {
        Scanner clavier = new Scanner(System.in);
        int width = askIntAbsolute("Enter the width of the board");
        int height = askIntAbsolute("Enter the height of the board");
        asciiPaint = new AsciiPaint(width, height);
        view.showCommand();
        while (!quit) {
            System.out.println("Enter a command : ");
            String command = clavier.nextLine();
            while (command.length() == 0) {
                System.out.println("Command invalid. \nEnter a command : ");
                command = clavier.nextLine();
            }
            List<String> commandPart = commandMatcher(command);
            if (commandPart != null) {
                if (commandComplete(commandPart)) {
                    System.out.println("Command complete");
                }
            } else {
                System.out.println("Command invalid");
            }

        }
    }

    /**
     * execute the command
     * @param commandPart command to execute
     */
    private boolean commandComplete(List<String> commandPart) {
        switch (commandPart.get(0).toLowerCase()) {
            case "add" -> {
                switch (commandPart.get(1).toLowerCase()) {
                    case "circle" -> {
                        return addCircle(commandPart);
                    }
                    case "rectangle" -> {
                        return addRectangle(commandPart);
                    }
                    case "square" -> {
                        return addSquare(commandPart);
                    }
                    case "line" -> {
                        return addLine(commandPart);
                    }
                    default -> {
                        return false;
                    }
                }
            }
            case "group" -> {
                return group(commandPart);
            }
            case "ungroup" -> {
                return ungroup(commandPart);
            }
            case "set" -> {
                return setColor(commandPart);
            }
            case "move" -> {
                return moveShape(commandPart);
            }
            case "delete" -> {
                return deleteShape(commandPart);
            }
            case "undo" -> {
                try {
                    asciiPaint.undo();
                    return true;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    return false;
                }
            }
            case "redo" -> {
                try {
                    asciiPaint.redo();
                    return true;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    return false;
                }
            }
            case "list" -> {
                view.showShapeList(asciiPaint.getDrawing().getShapesList());
                return true;
            }
            case "show" -> {
                view.display(asciiPaint.getDrawing());
            }
            case "exit" -> {
                quit = true;
                return true;
            }
            case "help" -> {
                view.showCommand();
                return true;
            }
            default -> {
                return false;
            }
        }
        return false;
    }

    /**
     * Convert a String to integer
     * @param str the string to convert
     * @return return a integer
     */
    private int convertToInteger(String str) {
        int val;
        try {
            val = Integer.parseInt(str);
        }
        catch (NumberFormatException e) {
            throw new IllegalArgumentException("the value is not a number");
        }
        return val;
    }

    /**
     * verify if the input of keyboard is an integer and is not under 0 or null
     * @param message the message to show
     * @return an Integer
     */
    private int askIntAbsolute(String message) {
        Scanner clavier = new Scanner(System.in);
        boolean done = false;
        int nb = 0;
        while (!done) {
            try {
                System.out.println(message);
                nb = clavier.nextInt();
                if (nb <= 0) {
                    throw new IllegalArgumentException();
                }
                done = true;
            } catch (Exception e) {
                clavier.nextLine(); // vider le Scanner
                System.out.println("Is not a number !");
            }
        }
        return nb;
    }

    /**
     * Add a new Circle to the paint
     * @param commandPart command partition to execute de command
     * @return true if the circle is added, false else.
     */
    private boolean addCircle(List<String> commandPart) {
        try {
            int x = convertToInteger(commandPart.get(2));
            int y = convertToInteger(commandPart.get(3));
            double radius = convertToInteger(commandPart.get(4));
            char color = commandPart.get(5).charAt(0);
            asciiPaint.newCircle(x, y, radius, color);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Add a new rectangle to the paint
     * @param commandPart command partition to execute de command
     * @return true if the rectangle is added, false else.
     */
    private boolean addRectangle(List<String> commandPart) {
        try {
            int x = convertToInteger(commandPart.get(2));
            int y = convertToInteger(commandPart.get(3));
            double width = convertToInteger(commandPart.get(4));
            double height = convertToInteger(commandPart.get(5));
            char color = commandPart.get(6).charAt(0);
            asciiPaint.newRectangle(x, y, width, height, color);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Add a new square to the paint
     * @param commandPart command partition to execute de command
     * @return true if the square is added, false else.
     */
    private boolean addSquare(List<String> commandPart) {
        try {
            int x = convertToInteger(commandPart.get(2));
            int y = convertToInteger(commandPart.get(3));
            double side = convertToInteger(commandPart.get(4));
            char color = commandPart.get(5).charAt(0);
            asciiPaint.newSquare(x, y, side, color);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Add a new line to the paint
     * @param commandPart command partition to execute de command
     * @return true if the line is added, false else.
     */
    private boolean addLine(List<String> commandPart) {
        try {
            int x1 = convertToInteger(commandPart.get(2));
            int y1 = convertToInteger(commandPart.get(3));
            int x2 = convertToInteger(commandPart.get(4));
            int y2 = convertToInteger(commandPart.get(5));
            char color = commandPart.get(6).charAt(0);
            asciiPaint.newLine(x1, y1, x2, y2, color);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Create a group with some shapes
     * @param commandPart command partition to execute de command
     * @return true if the group is created and added, false else.
     */
    private boolean group(List<String> commandPart) {
        try {
            char color = commandPart.get(1).charAt(0);
            int[] shapeIndex = new int[commandPart.size() - 2];
            for (int i = 0; i < shapeIndex.length; i++) {
                shapeIndex[i] = convertToInteger(commandPart.get(i + 2));
            }
            asciiPaint.group(color, shapeIndex);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * ungroup a group
     * @param commandPart command partition to execute de command
     * @return true if the group was ungrouped, false else.
     */
    private boolean ungroup(List<String> commandPart) {
        try {
            int pos = convertToInteger(commandPart.get(1));
            asciiPaint.ungroup(pos);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Set a new color to a shape
     * @param commandPart command partition to execute de command
     * @return true if the new color of the shape is set, false else.
     */
    private boolean setColor(List<String> commandPart) {
        try {
            int pos = convertToInteger(commandPart.get(1));
            asciiPaint.setShapeColor(pos, commandPart.get(2).charAt(0));
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Move a shape
     * @param commandPart command partition to execute de command
     * @return true if the shape was moved, false else.
     */
    private boolean moveShape(List<String> commandPart) {
        try {
            int pos = convertToInteger(commandPart.get(1));
            int dx = convertToInteger(commandPart.get(2));
            int dy = convertToInteger(commandPart.get(3));
            asciiPaint.moveShape(pos, dx, dy);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Delete a shape
     * @param commandPart command partition to execute de command
     * @return true if the shape was deleted, false else.
     */
    private boolean deleteShape(List<String> commandPart) {
        try {
            int pos = convertToInteger(commandPart.get(1));
            asciiPaint.deleteShape(pos);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Spit the command in different part
     * @param command the command to split
     * @return the splited command
     */
    public static List<String> commandMatcher(String command) {
        List<String> commandPart = new ArrayList<>();
        String patternCircleOrSquare = "(add)\\s+(circle|square)\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)\\s+([a-zA-Z])";
        String patternAddRecOrLine = "(add)\\s+(rectangle|line)\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)\\s+([a-zA-Z])";
        String patternMove = "(move)\\s+(\\d+)\\s+(-?\\d+)\\s+(-?\\d+)";
        String patternSetColor = "(set)\\s+(\\d+)\\s+([a-zA-Z])";
        String patternGroup = "(group)\\s+([a-zA-Z])\\s+(.*\\d+)";
        String patternGroupNumber = "(\\d+)";
        String patternUngroupDelete = "(ungroup|delete)\\s+(\\d+)";
        String patternOther = "(show|list|exit|help|undo|redo)";

        Pattern pAddCircleOrSquare = Pattern.compile(patternCircleOrSquare);
        Pattern pAddRecOrLine = Pattern.compile(patternAddRecOrLine);
        Pattern pMove = Pattern.compile(patternMove);
        Pattern pSetColor = Pattern.compile(patternSetColor);
        Pattern pGroup = Pattern.compile(patternGroup);
        Pattern pGroupNumber = Pattern.compile(patternGroupNumber);
        Pattern pUngroupOrDelete = Pattern.compile(patternUngroupDelete);
        Pattern pOther = Pattern.compile(patternOther);

        Matcher addCircleOrSquare = pAddCircleOrSquare.matcher(command);
        Matcher addRecOrLine = pAddRecOrLine.matcher(command);
        Matcher move = pMove.matcher(command);
        Matcher setColor = pSetColor.matcher(command);
        Matcher group = pGroup.matcher(command);
        Matcher ungroupOrDelete = pUngroupOrDelete.matcher(command);
        Matcher other = pOther.matcher(command);

        if(addCircleOrSquare.find()) {
            for (int i = 1; i <= 6; i++) {
                commandPart.add(addCircleOrSquare.group(i));
            }
            return commandPart;
        }
        else if (addRecOrLine.find()) {
            for (int i = 1; i <= 7; i++) {
                commandPart.add(addRecOrLine.group(i));
            }
            return commandPart;
        }
        else if(move.find()) {
            for (int i = 1; i <= 4; i++) {
                commandPart.add(move.group(i));
            }
            return commandPart;
        }
        else if(setColor.find()) {
            for (int i = 1; i <= 3; i++) {
                commandPart.add(setColor.group(i));
            }
            return commandPart;
        }
        else if(group.find()) {
            for (int i = 1; i <= 2; i++) {
                commandPart.add(group.group(i));
            }
            Matcher m_gNumber = pGroupNumber.matcher(group.group(3));
            int i = 2;
            while(m_gNumber.find()) {
                commandPart.add(m_gNumber.group());
                i++;
            }
            return commandPart;
        } else if (ungroupOrDelete.find()) {
            for (int i = 1; i <= 2; i++) {
                commandPart.add(ungroupOrDelete.group(i));
            }
            return commandPart;
        }
        else if(other.find()) {
            commandPart.add(other.group(1));
            return commandPart;
        }
        else {
            return null;
        }
    }

}

