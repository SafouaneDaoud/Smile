package tn.edu.espritCs.smile.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import tn.edu.espritCs.smile.dao.SponsorshipDao;
import tn.edu.espritCs.smile.dao.UserDao;
import tn.edu.espritCs.smile.domain.Sponsorship;
import tn.edu.espritCs.smile.domain.User;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class SponsorshipForm extends JFrame {

	private JPanel contentPane;
	private JLabel lblChildrenList;
	private JLabel lblSponsoredChildrenList;
	private JList listChildren;
	private JList listSponsoredChildren;
	private JButton btnSponsorChild;
	private JButton btnUnsponsorChild;

	private Integer currentIdChild;
	private String[] listChildrenNames;
	private int[] listChildrenIds;

	private Integer currentIdSponsoredChild;
	private String[] listSponsoredChildrenNames;
	private int[] listSponsoredChildrenIds;

	/**
	 * Create the frame.
	 */
	public SponsorshipForm() {
		setTitle("Sponsorship");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 558, 328);
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
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						RowSpec.decode("default:grow"),
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC, }));

		lblChildrenList = new JLabel("Childen's list");
		contentPane.add(lblChildrenList, "8, 2");

		lblSponsoredChildrenList = new JLabel("Sponsored childen's list");
		contentPane.add(lblSponsoredChildrenList, "22, 2");

		listChildren = new JList();
		listChildren.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				if (listChildrenIds.length > 0
						&& listChildrenIds.length > listChildren
								.getSelectedIndex()
						&& listChildren.getSelectedIndex() >= 0)
					currentIdChild = listChildrenIds[listChildren
							.getSelectedIndex()];
				else
					currentIdChild = 0;
			}
		});
		contentPane.add(listChildren, "2, 4, 13, 19, fill, fill");

		listSponsoredChildren = new JList();
		listSponsoredChildren
				.addListSelectionListener(new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent e) {
						if (listSponsoredChildrenIds.length > 0
								&& listSponsoredChildrenIds.length > listSponsoredChildren
										.getSelectedIndex()
								&& listSponsoredChildren.getSelectedIndex() >= 0)
							currentIdSponsoredChild = listSponsoredChildrenIds[listSponsoredChildren
									.getSelectedIndex()];
						else
							currentIdSponsoredChild = 0;
					}
				});
		contentPane.add(listSponsoredChildren, "18, 4, 9, 19, fill, fill");

		btnSponsorChild = new JButton("> Sponsor child >");
		btnSponsorChild.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SponsorshipDao sponsorshipDao = new SponsorshipDao();
				Sponsorship sponsorship = sponsorshipDao
						.findSponsorshipByDonorChildIds(
								LoginPage.currentUser.getIdUser(),
								currentIdChild);
				if (sponsorship == null && currentIdChild > 0) {
					sponsorship = new Sponsorship();
					sponsorship.setIdUserChild(currentIdChild);
					sponsorship.setIdUserDonor(LoginPage.currentUser
							.getIdUser());
					sponsorshipDao.addSponsorship(sponsorship);
				}
				loadLists();
			}
		});
		contentPane.add(btnSponsorChild, "16, 10");

		btnUnsponsorChild = new JButton("< Unsponsor Child <");
		btnUnsponsorChild.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SponsorshipDao sponsorshipDao = new SponsorshipDao();
				Sponsorship sponsorship = sponsorshipDao
						.findSponsorshipByDonorChildIds(
								LoginPage.currentUser.getIdUser(),
								currentIdSponsoredChild);
				if (sponsorship != null && currentIdSponsoredChild > 0) {
					sponsorshipDao.deleteSponsorshipById(sponsorship
							.getIdSponsorship());
				}
				loadLists();
			}
		});
		contentPane.add(btnUnsponsorChild, "16, 12");

		loadLists();
	}

	private void loadLists() {
		try {
			UserDao userDao = new UserDao();
			ArrayList<User> lstChildren = userDao.getAllUsersByRole("Child");
			ArrayList<User> lstSponsoredChildren = userDao
					.getAllUsersBySponsor(LoginPage.currentUser.getIdUser());

			listChildrenNames = new String[lstChildren.size()];
			listChildrenIds = new int[lstChildren.size()];

			listSponsoredChildrenNames = new String[lstSponsoredChildren.size()];
			listSponsoredChildrenIds = new int[lstSponsoredChildren.size()];

			for (int i = 0; i < lstChildren.size(); i++) {
				if (!lstSponsoredChildren.contains(lstChildren.get(i))) {
					User user = lstChildren.get(i);
					String dataUser = user.getFirstNameUser() + " "
							+ user.getLastNameUser();
					listChildrenNames[i] = dataUser;
					listChildrenIds[i] = user.getIdUser();
				}
			}

			for (int i = 0; i < lstSponsoredChildren.size(); i++) {
				User user = lstSponsoredChildren.get(i);
				String dataUser = user.getFirstNameUser() + " "
						+ user.getLastNameUser();
				listSponsoredChildrenNames[i] = dataUser;
				listSponsoredChildrenIds[i] = user.getIdUser();
			}

			listChildren.setSelectedIndex(0);
			listChildren.setListData(listChildrenNames);
			listSponsoredChildren.setSelectedIndex(0);
			listSponsoredChildren.setListData(listSponsoredChildrenNames);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
