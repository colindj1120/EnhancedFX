package io.github.colindj1120.enhancedfx.controls.simplecontrol.efxlabeled.base;

import io.github.colindj1120.enhancedfx.controls.simplecontrol.base.InnerBase;
import javafx.beans.property.*;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Labeled;
import javafx.scene.control.OverrunStyle;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public interface InnerLabeled<T extends Labeled> extends InnerBase<T> {
    default StringProperty textProperty()                           {return getInnerControl().textProperty();}

    default void setText(String value)                              {getInnerControl().setText(value);}

    default String getText()                                        {return getInnerControl().getText();}

    default ObjectProperty<Pos> alignmentProperty()                 {return getInnerControl().alignmentProperty();}

    default void setAlignment(Pos value)                            {getInnerControl().setAlignment(value);}

    default Pos getAlignment()                                      {return getInnerControl().getAlignment();}

    default ObjectProperty<TextAlignment> textAlignmentProperty()   {return getInnerControl().textAlignmentProperty();}

    default void setTextAlignment(TextAlignment value)              {getInnerControl().setTextAlignment(value);}

    default TextAlignment getTextAlignment()                        {return getInnerControl().getTextAlignment();}

    default ObjectProperty<OverrunStyle> textOverrunProperty()      {return getInnerControl().textOverrunProperty();}

    default void setTextOverrun(OverrunStyle value)                 {getInnerControl().setTextOverrun(value);}

    default OverrunStyle getTextOverrun()                           {return getInnerControl().getTextOverrun();}

    default StringProperty ellipsisStringProperty()                 {return getInnerControl().ellipsisStringProperty();}

    default void setEllipsisString(String value)                    {getInnerControl().setEllipsisString(value);}

    default String getEllipsisString()                              {return getInnerControl().getEllipsisString();}

    default BooleanProperty wrapTextProperty()                      {return getInnerControl().wrapTextProperty();}

    default void setWrapText(boolean value)                         {getInnerControl().setWrapText(value);}

    default boolean isWrapText()                                    {return getInnerControl().isWrapText();}

    default Orientation getContentBias()                            {return getInnerControl().getContentBias();}

    default ObjectProperty<Font> fontProperty()                     {return getInnerControl().fontProperty();}

    default void setFont(Font value)                                {getInnerControl().setFont(value);}

    default Font getFont()                                          {return getInnerControl().getFont();}

    default ObjectProperty<Node> graphicProperty()                  {return getInnerControl().graphicProperty();}

    default void setGraphic(Node value)                             {getInnerControl().setGraphic(value);}

    default Node getGraphic()                                       {return getInnerControl().getGraphic();}

    default BooleanProperty underlineProperty()                     {return getInnerControl().underlineProperty();}

    default void setUnderline(boolean value)                        {getInnerControl().setUnderline(value);}

    default boolean isUnderline()                                   {return getInnerControl().isUnderline();}

    default DoubleProperty lineSpacingProperty()                    {return getInnerControl().lineSpacingProperty();}

    default void setLineSpacing(double value)                       {getInnerControl().setLineSpacing(value);}

    default double getLineSpacing()                                 {return getInnerControl().getLineSpacing();}

    default ObjectProperty<ContentDisplay> contentDisplayProperty() {return getInnerControl().contentDisplayProperty();}

    default void setContentDisplay(ContentDisplay value)            {getInnerControl().setContentDisplay(value);}

    default ContentDisplay getContentDisplay()                      {return getInnerControl().getContentDisplay();}

    default ReadOnlyObjectProperty<Insets> labelPaddingProperty()   {return getInnerControl().labelPaddingProperty();}

    default Insets getLabelPadding()                                {return getInnerControl().getLabelPadding();}

    default DoubleProperty graphicTextGapProperty()                 {return getInnerControl().graphicTextGapProperty();}

    default void setGraphicTextGap(double value)                    {getInnerControl().setGraphicTextGap(value);}

    default double getGraphicTextGap()                              {return getInnerControl().getGraphicTextGap();}

    default void setTextFill(Paint value)                           {getInnerControl().setTextFill(value);}

    default Paint getTextFill()                                     {return getInnerControl().getTextFill();}

    default ObjectProperty<Paint> textFillProperty()                {return getInnerControl().textFillProperty();}

    default void setMnemonicParsing(boolean value)                  {getInnerControl().setMnemonicParsing(value);}

    default boolean isMnemonicParsing()                             {return getInnerControl().isMnemonicParsing();}

    default BooleanProperty mnemonicParsingProperty()               {return getInnerControl().mnemonicParsingProperty();}

}
