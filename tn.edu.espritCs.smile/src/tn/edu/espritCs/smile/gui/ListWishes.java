package tn.edu.espritCs.smile.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import tn.edu.espritCs.smile.dao.WishDao;
import tn.edu.espritCs.smile.domain.Wish;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class ListWishes extends JFrame {

	private JPanel contentPane;
	private JButton btnValidateWish;
	private JButton btnRespondeToWish;
	private JButton btnModify;
	private JList listBoxWishes;

	private Integer currentIdWish;
	private String[] listWishesDesc;
	private int[] listWishesIds;

	/**
	 * Create the frame.
	 */
	public ListWishes() {
		setTitle("List of wishes");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 448, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FormLayout(
				new ColumnSpec[] { FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"),
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC, }, new RowSpec[] {
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						RowSpec.decode("default:grow"), }));

		btnValidateWish = new JButton("Validate wish");
		btnValidateWish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (LoginPage.currentUser.getRoleUser() == "Admin") {
					WishDao wishDao = new WishDao();
					Wish wish = wishDao.findWishById(currentIdWish);
					wish.setStatusWish("Valid");
					wishDao.updateWish(wish);
				}
			}
		});
		contentPane.add(btnValidateWish, "26, 4");

		btnRespondeToWish = new JButton("Responde to wish");
		btnRespondeToWish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (LoginPage.currentUser.getRoleUser() == "Donor") {
					WishDao wishDao = new WishDao();
					Wish wish = wishDao.findWishById(currentIdWish);
					wish.setIdUserDonor(LoginPage.currentUser.getIdUser());
					wish.setStatusWish("Granted");
					wishDao.updateWish(wish);
				}
			}
		});
		contentPane.add(btnRespondeToWish, "26, 6");

		listBoxWishes = new JList();
		listBoxWishes.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				if (listWishesIds.length > 0
						&& listWishesIds.length > listBoxWishes
								.getSelectedIndex())
					currentIdWish = listWishesIds[listBoxWishes
							.getSelectedIndex()];
				else
					currentIdWish = 0;
			}
		});
		loadListWishes();
		listBoxWishes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		contentPane.add(listBoxWishes, "2, 4, 23, 7, fill, fill");

		btnModify = new JButton("Modify");
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (LoginPage.currentUser.getRoleUser() == "Child") {
					WishDao wishDao = new WishDao();
					Wish wish = wishDao.findWishById(currentIdWish);
					if (wish != null && currentIdWish != 0) {
						RequestWishFrame requestWishFrame = new RequestWishFrame(
								wish);
						requestWishFrame.setVisible(true);
					}
				}
			}
		});
		contentPane.add(btnModify, "26, 8");

		btnModify.setEnabled(LoginPage.currentUser.getRoleUser() == "Child");
		btnValidateWish
				.setEnabled(LoginPage.currentUser.getRoleUser() == "Admin");
		btnRespondeToWish
				.setEnabled(LoginPage.currentUser.getRoleUser() == "Donor");
	}

	private void loadListWishes() {
		try {
			WishDao wishDao = new WishDao();
			ArrayList<Wish> lstWishes;
			if (LoginPage.currentUser.getRoleUser() == "Child")
				lstWishes = wishDao.getAllChildWishes(LoginPage.currentUser
						.getIdUser());
			else
				lstWishes = wishDao.getAllWishes();
			listWishesDesc = new String[lstWishes.size()];
			listWishesIds = new int[lstWishes.size()];
			for (int i = 0; i < lstWishes.size(); i++) {
				Wish wish = lstWishes.get(i);
				String dataWish = "";
				if (wish.getDescriptionWish().length() > 40)
					dataWish = wish.getDescriptionWish().substring(0, 37)
							+ "...";
				else
					dataWish = wish.getDescriptionWish();
				listWishesDesc[i] = dataWish;
				listWishesIds[i] = wish.getIdWish();
			}
			listBoxWishes.setSelectedIndex(0);
			listBoxWishes.setListData(listWishesDesc);
		} catch (Exception e) {
		}
	}

}
