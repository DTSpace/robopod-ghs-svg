

import org.robovm.apple.coregraphics.CGRect;
import org.robovm.apple.coregraphics.CGSize;
import org.robovm.apple.foundation.NSError;
import org.robovm.apple.uikit.NSTextAlignment;
import org.robovm.apple.uikit.UIButton;
import org.robovm.apple.uikit.UIButtonType;
import org.robovm.apple.uikit.UIColor;
import org.robovm.apple.uikit.UIControlState;
import org.robovm.apple.uikit.UIFont;
import org.robovm.apple.uikit.UIImage;
import org.robovm.apple.uikit.UILabel;
import org.robovm.apple.uikit.UIView;
import org.robovm.apple.uikit.UIViewController;
import org.robovm.pods.ghs.svg.SVGRenderer;

public class MyViewController extends UIViewController {
    private final UIButton button;
    private final UILabel label;
    private int clickCount;
    private SVGRenderer renderer;

    public MyViewController() {
        // Get the view of this view controller.
        UIView view = getView();

        // Setup background.
        view.setBackgroundColor(UIColor.white());

        // Setup label.
        label = new UILabel(new CGRect(20, 250, 280, 44));
        label.setFont(UIFont.getSystemFont(24));
        label.setTextAlignment(NSTextAlignment.Center);
        view.addSubview(label);

        // Setup button.
        button = new UIButton(UIButtonType.RoundedRect);
        button.setFrame(new CGRect(110, 150, 100, 40));
        button.setTitle("Click me!", UIControlState.Normal);
        button.getTitleLabel().setFont(UIFont.getBoldSystemFont(22));

        button.addOnTouchUpInsideListener((control, event) -> label.setText("Click Nr. " + (++clickCount)));
        
        String svg = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> "
        		+ "<svg viewport-fill=\"none\"  x=\"0\" y=\"0\" width=\"100\" height=\"100\" viewBox=\"0, 0, 100, 100\" > "
        		+ "<g  fill=\"none\" stroke=\"none\">"
        		+ "<rect x=\"0\" y=\"0\" width=\"50\" height=\"50\" fill=\"#00FFAA\" stroke=\"#00FF00\" />"
        		+ "</g></svg>";
       
        System.out.flush();
        this.renderer = new SVGRenderer(svg);
        System.out.println("Renderer instanciated.");
        UIImage image = renderer.asImageWithSize(new CGSize(100, 100), 1.0);
        System.out.flush();
        System.out.println(image);
        System.out.flush();
        NSError error = this.renderer.getParserError();
        System.out.println("got parser error.");
        System.out.flush();
        System.out.println("is null: " + String.valueOf(error) + " and " + this.renderer.isHidden());
        System.out.flush();
        if (error != null) {
        		System.out.println(error.getLocalizedDescription());
        		System.out.println(error.getLocalizedFailureReason());
        		System.out.flush();
        }
        System.out.println(renderer.getViewRect());
        System.out.println("View Rect ok.");
        System.out.flush();

        //System.out.println("image: " + (image == null));
        System.out.println("button: ");
        System.out.println(button);
        System.out.flush();
        
        button.setBackgroundImage(image, UIControlState.Normal);
        
        view.addSubview(button);
    }
}
