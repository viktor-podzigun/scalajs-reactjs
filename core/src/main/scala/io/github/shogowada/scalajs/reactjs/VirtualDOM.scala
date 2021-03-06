package io.github.shogowada.scalajs.reactjs

import io.github.shogowada.scalajs.reactjs.React.Render
import io.github.shogowada.scalajs.reactjs.VirtualDOM.VirtualDOMAttributes.Type.AS_IS
import io.github.shogowada.scalajs.reactjs.VirtualDOM.VirtualDOMElements.ReactClassElementSpec
import io.github.shogowada.scalajs.reactjs.classes.ReactClass
import io.github.shogowada.scalajs.reactjs.elements.ReactElement
import io.github.shogowada.scalajs.reactjs.events._
import io.github.shogowada.statictags.AttributeValueType.AttributeValueType
import io.github.shogowada.statictags._
import org.scalajs.dom.raw.HTMLElement

import scala.language.implicitConversions
import scala.scalajs.js
import scala.scalajs.js.JSConverters._

object VirtualDOM extends VirtualDOM

object EventVirtualDOMAttributes {
  trait OnEventAttribute[Event <: SyntheticEvent] extends AttributeSpec {
    val name: String

    def :=(callback: js.Function0[_]): Attribute[js.Function0[_]] = {
      Attribute(name, callback, AS_IS)
    }

    def :=(callback: js.Function1[Event, _]): Attribute[js.Function1[Event, _]] = {
      Attribute(name, callback, AS_IS)
    }
  }

  case class OnAnimationEventAttribute(name: String) extends OnEventAttribute[AnimationSyntheticEvent]
  case class OnClipboardEventAttribute(name: String) extends OnEventAttribute[ClipboardSyntheticEvent]
  case class OnCompositionEventAttribute(name: String) extends OnEventAttribute[CompositionSyntheticEvent]
  case class OnFocusEventAttribute(name: String) extends OnEventAttribute[FocusSyntheticEvent]
  case class OnFormEventAttribute[FormEvent <: FormSyntheticEvent[_]](name: String) extends OnEventAttribute[FormEvent]
  case class OnImageEventAttribute(name: String) extends OnEventAttribute[ImageSyntheticEvent]
  case class OnKeyboardEventAttribute(name: String) extends OnEventAttribute[KeyboardSyntheticEvent]
  case class OnMediaEventAttribute(name: String) extends OnEventAttribute[MediaSyntheticEvent]
  case class OnMouseEventAttribute(name: String) extends OnEventAttribute[MouseSyntheticEvent]
  case class OnSelectionEventAttribute(name: String) extends OnEventAttribute[SelectionSyntheticEvent]
  case class OnTouchEventAttribute(name: String) extends OnEventAttribute[TouchSyntheticEvent]
  case class OnTransitionEventAttribute(name: String) extends OnEventAttribute[TransitionSyntheticEvent]
  case class OnUIEventAttribute(name: String) extends OnEventAttribute[UISyntheticEvent]
  case class OnWheelEventAttribute(name: String) extends OnEventAttribute[WheelSyntheticEvent]
  case class OnErrorEventAttribute(name: String) extends OnEventAttribute[SyntheticEvent]
}

trait EventVirtualDOMAttributes {

  import EventVirtualDOMAttributes._

  // Animation Events
  lazy val onAnimationStart = OnAnimationEventAttribute("onAnimationStart")
  lazy val onAnimationEnd = OnAnimationEventAttribute("onAnimationEnd")
  lazy val onAnimationIteration = OnAnimationEventAttribute("onAnimationIteration")

  // Clipboard Events
  lazy val onCopy = OnClipboardEventAttribute("onCopy")
  lazy val onCut = OnClipboardEventAttribute("onCut")
  lazy val onPaste = OnClipboardEventAttribute("onPaste")

  // Composition Events
  lazy val onCompositionEnd = OnCompositionEventAttribute("onCompositionEnd")
  lazy val onCompositionStart = OnCompositionEventAttribute("onCompositionStart")
  lazy val onCompositionUpdate = OnCompositionEventAttribute("onCompositionUpdate")

  // Focus Events
  lazy val onFocus = OnFocusEventAttribute("onFocus")
  lazy val onBlur = OnFocusEventAttribute("onBlur")

  // Form Events
  lazy val onChange = OnFormEventAttribute("onChange")
  lazy val onInput = OnFormEventAttribute("onInput")
  lazy val onSubmit = OnFormEventAttribute("onSubmit")

  // Image Events
  lazy val onLoad = OnImageEventAttribute("onLoad")

  // onError conflicts with Media Events. Conflicts are treated specially at the bottom of this trait.
  // lazy val onError = OnImageEventAttribute("onError")

  // Keyboard Events
  lazy val onKeyDown = OnKeyboardEventAttribute("onKeyDown")
  lazy val onKeyPress = OnKeyboardEventAttribute("onKeyPress")
  lazy val onKeyUp = OnKeyboardEventAttribute("onKeyUp")

  // Media Events
  lazy val onAbort = OnMediaEventAttribute("onAbort")
  lazy val onCanPlay = OnMediaEventAttribute("onCanPlay")
  lazy val onCanPlayThrough = OnMediaEventAttribute("onCanPlayThrough")
  lazy val onDurationChange = OnMediaEventAttribute("onDurationChange")
  lazy val onEmptied = OnMediaEventAttribute("onEmptied")
  lazy val onEncrypted = OnMediaEventAttribute("onEncrypted")
  lazy val onEnded = OnMediaEventAttribute("onEnded")
  // onError conflicts with Image Events. Conflicts are treated specially at the bottom of this trait.
  // lazy val onError = OnMediaEventAttribute("onError")
  lazy val onLoadedData = OnMediaEventAttribute("onLoadedData")
  lazy val onLoadedMetadata = OnMediaEventAttribute("onLoadedMetadata")
  lazy val onLoadStart = OnMediaEventAttribute("onLoadStart")
  lazy val onPause = OnMediaEventAttribute("onPause")
  lazy val onPlay = OnMediaEventAttribute("onPlay")
  lazy val onPlaying = OnMediaEventAttribute("onPlaying")
  lazy val onProgress = OnMediaEventAttribute("onProgress")
  lazy val onRateChange = OnMediaEventAttribute("onRateChange")
  lazy val onSeeked = OnMediaEventAttribute("onSeeked")
  lazy val onSeeking = OnMediaEventAttribute("onSeeking")
  lazy val onStalled = OnMediaEventAttribute("onStalled")
  lazy val onSuspend = OnMediaEventAttribute("onSuspend")
  lazy val onTimeUpdate = OnMediaEventAttribute("onTimeUpdate")
  lazy val onVolumeChange = OnMediaEventAttribute("onVolumeChange")
  lazy val onWaiting = OnMediaEventAttribute("onWaiting")

  // Mouse Events
  lazy val onClick = OnMouseEventAttribute("onClick")
  lazy val onContextMenu = OnMouseEventAttribute("onContextMenu")
  lazy val onDoubleClick = OnMouseEventAttribute("onDoubleClick")
  lazy val onDrag = OnMouseEventAttribute("onDrag")
  lazy val onDragEnd = OnMouseEventAttribute("onDragEnd")
  lazy val onDragEnter = OnMouseEventAttribute("onDragEnter")
  lazy val onDragExit = OnMouseEventAttribute("onDragExit")
  lazy val onDragLeave = OnMouseEventAttribute("onDragLeave")
  lazy val onDragOver = OnMouseEventAttribute("onDragOver")
  lazy val onDragStart = OnMouseEventAttribute("onDragStart")
  lazy val onDrop = OnMouseEventAttribute("onDrop")
  lazy val onMouseDown = OnMouseEventAttribute("onMouseDown")
  lazy val onMouseEnter = OnMouseEventAttribute("onMouseEnter")
  lazy val onMouseLeave = OnMouseEventAttribute("onMouseLeave")
  lazy val onMouseMove = OnMouseEventAttribute("onMouseMove")
  lazy val onMouseOut = OnMouseEventAttribute("onMouseOut")
  lazy val onMouseOver = OnMouseEventAttribute("onMouseOver")
  lazy val onMouseUp = OnMouseEventAttribute("onMouseUp")

  // Selection Events
  lazy val onSelect = OnSelectionEventAttribute("onSelect")

  // Touch Events
  lazy val onTouchCancel = OnTouchEventAttribute("onTouchCancel")
  lazy val onTouchEnd = OnTouchEventAttribute("onTouchEnd")
  lazy val onTouchMove = OnTouchEventAttribute("onTouchMove")
  lazy val onTouchStart = OnTouchEventAttribute("onTouchStart")

  // Transition Events
  lazy val onTransitionEnd = OnTransitionEventAttribute("onTransitionEnd")

  // UI Events
  lazy val onScroll = OnUIEventAttribute("onScroll")

  // Wheel Events
  lazy val onWheel = OnWheelEventAttribute("onWheel")

  // Events that conflicted with other events
  lazy val onError = OnErrorEventAttribute("onError")
}

trait VirtualDOM extends StaticTags {

  class VirtualDOMElements extends Elements {
    def apply(reactClass: ReactClass): ReactClassElementSpec = ReactClassElementSpec(reactClass)
  }

  object VirtualDOMElements {
    case class ReactClassElementSpec(
        reactClass: ReactClass
    ) {
      def apply(attributes: Any*)(contents: Any*): ReactElement = {
        React.createElement(
          reactClass,
          VirtualDOMAttributes.toReactAttributes(Element.flattenAttributes(attributes)),
          toReactElements(Element.flattenContents(contents)): _*
        )
      }

      def empty = apply()()
    }

    def toReactElements(contents: Seq[Any]): Seq[js.Any] =
      contents.map(elementToReactElement)

    private def elementToReactElement(content: Any): js.Any =
      content match {
        case element@Element(_, _, _, _) => elementsToVirtualDOMs(element)
        case _ => content.asInstanceOf[js.Any]
      }
  }

  class VirtualDOMAttributes extends Attributes
      with EventVirtualDOMAttributes {

    import VirtualDOMAttributes._

    lazy val className = SpaceSeparatedStringAttributeSpec("className")
    override lazy val `for`: ForAttributeSpec = htmlFor
    lazy val htmlFor = ForAttributeSpec("htmlFor")
    lazy val key = StringAttributeSpec("key")
    lazy val ref = RefAttributeSpec("ref")
    lazy val wrapped = WrappedPropsAttributeSpec("wrapped")
  }

  object VirtualDOMAttributes {
    case class WrappedPropsAttributeSpec(name: String) extends AttributeSpec {
      def :=[T](wrappedProps: T): Attribute[js.Any] =
        Attribute(name, wrappedProps.asInstanceOf[js.Any], AS_IS)
    }

    case class ReactClassAttributeSpec(name: String) extends AttributeSpec {
      def :=(value: ReactClass): Attribute[ReactClass] = Attribute(name, value, AS_IS)
    }

    case class RefAttributeSpec(name: String) extends AttributeSpec {
      def :=[T <: HTMLElement](callback: js.Function1[T, _]): Attribute[js.Function1[T, _]] = {
        Attribute(name, callback, AS_IS)
      }
    }

    case class RenderAttributeSpec(name: String) extends AttributeSpec {
      def :=[WrappedProps](render: Render[WrappedProps]) = {
        val nativeRender = React.renderToNative(render)
        Attribute(name, nativeRender, AS_IS)
      }
    }

    object Type {
      case object AS_IS extends AttributeValueType
    }

    private lazy val htmlNameToReactNameMap = Map(
      "accept-charset" -> "acceptCharset",
      "accesskey" -> "accessKey",
      "allowfullscreen" -> "allowFullScreen",
      "autocomplete" -> "autoComplete",
      "autofocus" -> "autoFocus",
      "autoplay" -> "autoPlay",
      "charset" -> "charSet",
      "colspan" -> "colSpan",
      "contenteditable" -> "contentEditable",
      "contextmenu" -> "contextMenu",
      "crossorigin" -> "crossOrigin",
      "datetime" -> "dateTime",
      "enctype" -> "encType",
      "formaction" -> "formAction",
      "formenctype" -> "formEncType",
      "formmethod" -> "formMethod",
      "formnovalidate" -> "formNoValidate",
      "formtarget" -> "formTarget",
      "hreflang" -> "hrefLang",
      "http-equiv" -> "httpEquiv",
      "inputmode" -> "inputMode",
      "keytype" -> "keyType",
      "maxlength" -> "maxLength",
      "mediagroup" -> "mediaGroup",
      "minlength" -> "minLength",
      "novalidate" -> "noValidate",
      "radiogroup" -> "radioGroup",
      "spellcheck" -> "spellCheck",
      "srcdoc" -> "srcDoc",
      "srclang" -> "srcLang",
      "srcset" -> "srcSet",
      "tabindex" -> "tabIndex",
      "usemap" -> "useMap"
    )

    private def toReactAttributeName(name: String): String = {
      htmlNameToReactNameMap.getOrElse(name, name)
    }

    def toReactAttributes(attributes: Iterable[Attribute[_]]): js.Dictionary[js.Any] =
      attributes
          .map(attributeToReactAttribute)
          .toMap
          .toJSDictionary

    private def attributeToReactAttribute(attribute: Attribute[_]): (String, js.Any) =
      toReactAttributeName(attribute.name) -> attributeValueToReactAttributeValue(attribute)

    private def attributeValueToReactAttributeValue(attribute: Attribute[_]): js.Any =
      attribute match {
        case Attribute(_, value, AttributeValueType.CSS) => value.asInstanceOf[Map[String, _]].toJSDictionary
        case Attribute(_, value: Boolean, AttributeValueType.DEFAULT) => value.asInstanceOf[js.Any]
        case Attribute(_, value, AS_IS) => value.asInstanceOf[js.Any]
        case _ => attribute.valueToString
      }
  }

  override val < = new VirtualDOMElements()
  override val ^ = new VirtualDOMAttributes()

  implicit class RichElement(element: Element) {
    def asReactElement: ReactElement = {
      elementsToVirtualDOMs(element)
    }
  }

  implicit def elementsToVirtualDOMs(element: Element): ReactElement =
    React.createElement(
      element.name,
      VirtualDOMAttributes.toReactAttributes(element.flattenedAttributes),
      VirtualDOMElements.toReactElements(element.flattenedContents): _*
    )
}
