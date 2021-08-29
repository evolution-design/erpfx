package Forms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

/**
 * CloseableTabbedPane is a tabbed pane with a close icon on the right side of all tabs making it possible to close a tab.
 * You can pass an instance of TabClosingListener to one of the constructors to react to tab closing.
 *
 * @author WiR
 */
public class CloseableTabbedPane extends JTabbedPane {

    public static interface TabClosingListener {
        /**
         * @param aTabIndex the index of the tab that is about to be closed
         * @return true if the tab can be really closed
         */
        public boolean tabClosing(int aTabIndex);

        /**
         * @param aTabIndex the index of the tab that is about to be closed
         * @return true if the tab should be selected before closing
         */
        public boolean selectTabBeforeClosing(int aTabIndex);
    }

    private TabClosingListener tabClosingListener;
    private String iconFileName = "img/window-close-icon_1.png";
    private String selectedIconFileName = "img/close-window-16.png";

    private static Icon CLOSING_ICON;
    private static Icon CLOSING_ICON_SELECTED;

    private class PaintedCrossIcon implements Icon {

        int size = 10;

        @Override
        public void paintIcon(Component c, Graphics g, int x, int y) {
            g.drawLine(x, y, x + size, y + size);
            g.drawLine(x + size, y, x, y + size);
        }

        @Override
        public int getIconWidth() {
            return size;
        }

        @Override
        public int getIconHeight() {
            return size;
        }

    }

    public CloseableTabbedPane() {
        super();
    }

    public CloseableTabbedPane(TabClosingListener aTabClosingListener) {
        super();
        tabClosingListener = aTabClosingListener;
    }

    /**
     * Sets the file name of the closing icon along with the optional variant of the icon when the mouse is over the icon.
     */
    public void setClosingIconFileName(String aIconFileName, String aSelectedIconFileName) {
        iconFileName = aIconFileName;
        selectedIconFileName = aSelectedIconFileName;
    }

    /**
     * Makes the close button at the specified indes visible or invisible
     */
    public void setCloseButtonVisibleAt(int aIndex, boolean aVisible) {
        CloseButtonTab cbt = (CloseButtonTab) getTabComponentAt(aIndex);
        cbt.closingLabel.setVisible(aVisible);
    }

    @Override
    public void insertTab(String title, Icon icon, Component component, String tip, int index) {
        super.insertTab(title, icon, component, tip, index);
        setTabComponentAt(index, new CloseButtonTab(component, title, icon));
    }

    @Override
    public void setTitleAt(int index, String title) {
        super.setTitleAt(index, title);
        CloseButtonTab cbt = (CloseButtonTab) getTabComponentAt(index);
        cbt.label.setText(title);
    }

    @Override
    public void setIconAt(int index, Icon icon) {
        super.setIconAt(index, icon);
        CloseButtonTab cbt = (CloseButtonTab) getTabComponentAt(index);
        cbt.label.setIcon(icon);
    }

    @Override
    public void setComponentAt(int index, Component component) {
        CloseButtonTab cbt = (CloseButtonTab) getTabComponentAt(index);
        super.setComponentAt(index, component);
        cbt.tab = component;
    }

    private Icon getImageIcon(String aImageName) {
        URL imageUrl = CloseableTabbedPane.class.getClassLoader().getResource(aImageName);
        if (imageUrl == null) {
            return new PaintedCrossIcon();
        }
        ImageIcon result = new ImageIcon(imageUrl);
        if (result.getIconWidth() != -1) {
            return result;
        } else {
            return null;
        }


    }

    class CloseButtonTab extends JPanel  {
        private Component tab;
        private JLabel label;
        private JLabel closingLabel;

        public CloseButtonTab(Component aTab, String aTitle, Icon aIcon) {
            tab = aTab;
            setOpaque(false);
            setLayout(new GridBagLayout());
            setVisible(true);

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(0, 0, 0, 5);

            label = new JLabel(aTitle);
            label.setIcon(aIcon);
            add(label, gbc);
            if (CLOSING_ICON == null) {
                CLOSING_ICON = getImageIcon(iconFileName);
                CLOSING_ICON_SELECTED = getImageIcon(selectedIconFileName);
            }
            closingLabel = new JLabel(CLOSING_ICON);
            closingLabel.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    JTabbedPane tabbedPane = (JTabbedPane) getParent().getParent();
                    int tabIndex = indexOfComponent(tab);
                    if (tabClosingListener != null) {
                        if (tabClosingListener.selectTabBeforeClosing(tabIndex)) {
                            tabbedPane.setSelectedIndex(tabIndex);
                        }
                        if (tabClosingListener.tabClosing(tabIndex)) {
                            tabbedPane.removeTabAt(tabIndex);
                        }
                    } else {
                        tabbedPane.removeTabAt(tabIndex);
                    }
                }


                @Override
                public void mouseEntered(MouseEvent e) {
                    if (CLOSING_ICON_SELECTED != null) {
                        closingLabel.setIcon(CLOSING_ICON_SELECTED);
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    if (CLOSING_ICON_SELECTED != null) {
                        closingLabel.setIcon(CLOSING_ICON);
                    }
                }
            });
            gbc.insets = new Insets(0, 0, 0, 0);
            add(closingLabel, gbc);
        }
    }
}