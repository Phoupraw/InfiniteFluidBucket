package phoupraw.mcmod.infinite_fluid_bucket.config;

import com.google.common.collect.Sets;
import it.unimi.dsi.fastutil.objects.Object2BooleanMap;
import it.unimi.dsi.fastutil.objects.Object2BooleanOpenHashMap;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.world.EditGameRulesScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.ThreePartsLayoutWidget;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;
import phoupraw.mcmod.infinite_fluid_bucket.constant.IFBGameRules;

import java.util.Set;

public class IFBEditGameRulesScreen extends Screen {
    public static final int GAP = 8;
    public static final Text TITLE = Text.translatable("editGamerule.title");
    public final ThreePartsLayoutWidget layout = new ThreePartsLayoutWidget(this);
    public final Set<EditGameRulesScreen.AbstractRuleWidget> invalidRuleWidgets = Sets.newHashSet();
    public final Object2BooleanMap<String> values = new Object2BooleanOpenHashMap<>(IFBGameRules.VALUES);
    public @Nullable EditGameRulesScreen.RuleListWidget ruleListWidget;
    public @Nullable ButtonWidget doneButton;
    public IFBEditGameRulesScreen() {
        super(TITLE);
    }
    //@Override
    //protected void init() {
    //    this.layout.addHeader(TITLE, this.textRenderer);
    //    this.ruleListWidget = this.layout.addBody(new EditGameRulesScreen.RuleListWidget(this.values));
    //    DirectionalLayoutWidget directionalLayoutWidget = this.layout.addFooter(DirectionalLayoutWidget.horizontal().spacing(8));
    //    this.doneButton = directionalLayoutWidget.add(ButtonWidget.builder(ScreenTexts.DONE, button -> this.ruleSaver.accept(Optional.of(this.gameRules))).build());
    //    directionalLayoutWidget.add(ButtonWidget.builder(ScreenTexts.CANCEL, button -> this.close()).build());
    //    this.layout.forEachChild(child -> {
    //        ClickableWidget var10000 = this.addDrawableChild(child);
    //    });
    //    this.initTabNavigation();
    //}
}
