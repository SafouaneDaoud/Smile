package tn.edu.espritCs.smile.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import tn.edu.espritCs.smile.dao.WishDao;
import tn.edu.espritCs.smile.domain.Wish;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class RequestWishFrame extends JFrame {

	private JPanel contentPane;
	private JLabel lblWishDescription;
	private JTextPane txtDescription;
	private JButton btnSaveWish;

	/**
	 * Create the frame.
	 */
	public RequestWishFrame(final Wish wish) {
		setTitle("Wishing form");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FormLayout(
				new ColumnSpec[] { FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"),
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"),
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC, }, new RowSpec[] {
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						RowSpec.decode("default:grow"),
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC, }));

		loadFields(wish);
		btnSaveWish = new JButton("Save");
		btnSaveWish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				WishDao wishDao = new WishDao();
				wish.setDescriptionWish(txtDescription.getText());
				if (LoginPage.currentUser.getRoleUser() == "Child") {
					wish.setIdUserChild(LoginPage.currentUser.getIdUser());
					wish.setStatusWish("Requested");
				}
				if (wish.getIdWish() == 0)
					wishDao.addWish(wish);
				else
					wishDao.updateWish(wish);
			}
		});
		contentPane.add(btnSaveWish, "4, 2");

		lblWishDescription = new JLabel("Wish Description:");
		contentPane.add(lblWishDescription, "4, 4, default, top");

		txtDescription = new JTextPane();
		contentPane.add(txtDescription, "6, 4, 3, 1, fill, fill");
	}

	private void loadFields(Wish wish) {
		try {
			if (wish != null) {
				txtDescription.setText(wish.getDescriptionWish());
			}
		} catch (Exception e) {

		}
	}

}
