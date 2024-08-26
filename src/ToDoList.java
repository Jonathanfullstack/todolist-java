import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToDoList extends JFrame implements ActionListener {

    // Componentes da interface
    private DefaultListModel<String> listModel;
    private JList<String> taskList;
    private JTextField taskField;
    private JButton addButton, deleteButton, completeButton;

    public ToDoList() {
        // Configuração da janela principal
        setTitle("Lista de Tarefas");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(60, 63, 65)); // Cor de fundo da janela

        // Modelo de lista para armazenar tarefas
        listModel = new DefaultListModel<>();
        taskList = new JList<>(listModel);
        taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        taskList.setFont(new Font("Arial", Font.PLAIN, 18));
        taskList.setBackground(new Color(43, 43, 43)); // Cor de fundo da lista
        taskList.setForeground(Color.WHITE); // Cor do texto da lista
        taskList.setSelectionBackground(new Color(75, 110, 175)); // Cor de fundo ao selecionar um item
        taskList.setSelectionForeground(Color.WHITE); // Cor do texto ao selecionar um item

        JScrollPane scrollPane = new JScrollPane(taskList);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Margens da lista de tarefas

        // Campo de texto para adicionar novas tarefas
        taskField = new JTextField();
        taskField.setFont(new Font("Arial", Font.PLAIN, 18));
        taskField.setBackground(new Color(69, 73, 74)); // Cor de fundo do campo de texto
        taskField.setForeground(Color.WHITE); // Cor do texto do campo de texto
        taskField.setCaretColor(Color.WHITE); // Cor do cursor de entrada de texto

        // Botões de ação
        addButton = new JButton("Adicionar Tarefa");
        deleteButton = new JButton("Excluir Tarefa");
        completeButton = new JButton("Completar Tarefa");

        // Estilização dos botões
        styleButton(addButton, new Color(28, 184, 65)); // Verde
        styleButton(deleteButton, new Color(219, 50, 54)); // Vermelho
        styleButton(completeButton, new Color(66, 133, 244)); // Azul

        // Adiciona os botões e o campo de texto ao painel de entrada
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(1, 4, 10, 10));
        inputPanel.setBackground(new Color(60, 63, 65)); // Cor de fundo do painel de entrada
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Margens do painel de entrada
        inputPanel.add(taskField);
        inputPanel.add(addButton);
        inputPanel.add(deleteButton);
        inputPanel.add(completeButton);

        // Adiciona componentes à janela principal
        add(scrollPane, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.SOUTH);

        // Adiciona ActionListeners aos botões
        addButton.addActionListener(this);
        deleteButton.addActionListener(this);
        completeButton.addActionListener(this);

        setVisible(true);
    }

    // Método para estilizar os botões
    private void styleButton(JButton button, Color color) {
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(60, 63, 65)),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            String task = taskField.getText().trim();
            if (!task.isEmpty()) {
                listModel.addElement(task);
                taskField.setText("");
            }
        } else if (e.getSource() == deleteButton) {
            int selectedIndex = taskList.getSelectedIndex();
            if (selectedIndex != -1) {
                listModel.remove(selectedIndex);
            }
        } else if (e.getSource() == completeButton) {
            int selectedIndex = taskList.getSelectedIndex();
            if (selectedIndex != -1) {
                String completedTask = listModel.getElementAt(selectedIndex) + " (Concluída)";
                listModel.set(selectedIndex, completedTask);
                taskList.clearSelection();
            }
        }
    }

    public static void main(String[] args) {
        new ToDoList();
    }
}
