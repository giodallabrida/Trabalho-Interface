package trabalhointerface.telas;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import trabalhointerface.modelo.ProdutoDTO;
import trabalhointerface.persistencia.ProdutoDAO;

public class Vendas extends javax.swing.JFrame {

    public Vendas() {
        initComponents();
        this.setLocationRelativeTo(null);
        criaBotoes();
    }

    public Object[][] mprodutos = null;

    public void criaBotoes() {
        //painel.setLayout(null);
        //painel.setPreferredSize(painel.getPreferredSize());

        
        // posições nos eixos X e Y de cada botão...
        int posX = 25;
        int posY = 20;

        ProdutoDAO produto = new ProdutoDAO();
        ArrayList<ProdutoDTO> listaProdutos = produto.carregaProdutos();
        mprodutos = new Object[listaProdutos.size()][2];
        int linha = 0;
        for (ProdutoDTO pdto : listaProdutos) {
            JButton botao = new JButton();
            botao.setName(String.valueOf(pdto.getCodigo()));
            mprodutos[linha][0] = botao;
            mprodutos[linha][1] = pdto;
            linha++;
            botao.setText("0");
            botao.setToolTipText(pdto.getNome() + " - R$" + pdto.getPreco());
            botao.setBounds(posX, posY, 135, 60);
            botao.setIcon(pdto.getIcone());

            // o método setBounds serve para definir a posição nos eixos x e y, além
            // da largura e altura do botão.
            botao.setVisible(true);

            // adicionar um ActionListener em um controle permite que o programa
            // possa responder a eventos específicos. o ActionListener adicionado
            // desvia a execução do código para o método "processaPressionamentoBotao()"
            // no qual é possível "identificar" qual foi o botão pressionado.
            botao.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    processaPressionamentoBotao(e);
                }
            });

            // o botão criado acima precisa ser adicionado ao JFrame (isso não é feito
            // de modo automático).
            // this = JFrame. getContentPane() = método para acessar o container de controles do JFrame.
            //this.setContentPane(scroll);
            painel.add(botao);

            // incrementa a posição no eixo Y para evitar a sobreposição dos botões.
            posX += 165;
            painel.add(botao);

            if (posX >= 645) {
                posX = 25;
                posY += 90;
                if (posY >= 400) {

                    //ativar barra de rolagem
                }
            }
        }
        /*
        scroll = new JScrollPane(painel);
        scroll.add(painel);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        //scroll.setBounds(50, 30, 300, 50);
        painelP.setPreferredSize(painel.getPreferredSize());
        painelP.add(scroll);
        //this.setContentPane(painelP);
        this.pack();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        
        
        
        scroll = new JScrollPane(painel);
        scroll.setPreferredSize(painel.getPreferredSize());
        //scroll.add(painel);
        //scroll.setViewportView(painel);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);*/
        this.repaint();
    }

    private void processaPressionamentoBotao(ActionEvent e) {
        JButton botao = (JButton) e.getSource();
        botao.setText(String.valueOf(Integer.valueOf(botao.getText()) + 1));
        //if (e.getActionCommand() == ){
            
        //}
    }
    
    /*public void fazConta(){
        String vetor[]; 
        JButton botao = new JButton();
        for (int l = 0; l < mprodutos.length; l++) {
         //   botao = mprodutos[l][0];
            vetor = botao.getToolTipText().split("$");
        }
*/
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        painelP = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        total = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        btnTotal = new javax.swing.JButton();
        btnVoltar = new javax.swing.JButton();
        scroll = new javax.swing.JScrollPane();
        painel = new javax.swing.JPanel();
        encerrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Vendas");
        setMinimumSize(new java.awt.Dimension(758, 479));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        painelP.setBackground(new java.awt.Color(153, 255, 204));
        painelP.setAutoscrolls(true);

        jLabel2.setFont(new java.awt.Font("Baskerville Old Face", 1, 36)); // NOI18N
        jLabel2.setText("Vendas");

        total.setEditable(false);

        jLabel1.setFont(new java.awt.Font("Baskerville Old Face", 0, 24)); // NOI18N
        jLabel1.setText("Total");

        btnTotal.setFont(new java.awt.Font("Baskerville Old Face", 0, 18)); // NOI18N
        btnTotal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/coins.png"))); // NOI18N
        btnTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTotalActionPerformed(evt);
            }
        });

        btnVoltar.setFont(new java.awt.Font("Baskerville Old Face", 0, 18)); // NOI18N
        btnVoltar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/002-voltar.png"))); // NOI18N
        btnVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltarActionPerformed(evt);
            }
        });

        scroll.setBackground(new java.awt.Color(153, 255, 204));
        scroll.setToolTipText("");
        scroll.setAutoscrolls(true);

        painel.setBackground(new java.awt.Color(153, 255, 204));
        painel.setAutoscrolls(true);
        painel.setPreferredSize(new java.awt.Dimension(100, 100));

        javax.swing.GroupLayout painelLayout = new javax.swing.GroupLayout(painel);
        painel.setLayout(painelLayout);
        painelLayout.setHorizontalGroup(
            painelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 699, Short.MAX_VALUE)
        );
        painelLayout.setVerticalGroup(
            painelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 333, Short.MAX_VALUE)
        );

        scroll.setViewportView(painel);

        encerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/maquina.png"))); // NOI18N
        encerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                encerrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout painelPLayout = new javax.swing.GroupLayout(painelP);
        painelP.setLayout(painelPLayout);
        painelPLayout.setHorizontalGroup(
            painelPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelPLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addComponent(btnTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(encerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(painelPLayout.createSequentialGroup()
                .addGroup(painelPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelPLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator1))
                    .addGroup(painelPLayout.createSequentialGroup()
                        .addGroup(painelPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(painelPLayout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 692, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(painelPLayout.createSequentialGroup()
                                .addGap(316, 316, 316)
                                .addComponent(jLabel2)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(painelPLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(scroll)))
                .addContainerGap())
        );
        painelPLayout.setVerticalGroup(
            painelPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelPLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnVoltar, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                    .addGroup(painelPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, painelPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(encerrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarActionPerformed
        Menu menu = new Menu();
        menu.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnVoltarActionPerformed

    

    private void btnTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTotalActionPerformed

        //total.setText(String.valueOf(contaTotal));
    }//GEN-LAST:event_btnTotalActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        System.exit(0);
    }//GEN-LAST:event_formWindowClosed

    private void encerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_encerrarActionPerformed
        //Relatorio rel = new Relatorio();
        //rel.setTotalVendas(rel.getTotalVendas() + Float.valueOf(total.getText()));
    }//GEN-LAST:event_encerrarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTotal;
    private javax.swing.JButton btnVoltar;
    private javax.swing.JButton encerrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPanel painel;
    private javax.swing.JPanel painelP;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JTextField total;
    // End of variables declaration//GEN-END:variables

}
