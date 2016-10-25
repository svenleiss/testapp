import MessageStrings from "insideApp/messages";
import moment from "moment";

const LoadLocale = Reflux.createAction({asyncResult: true});

function setLocale(locale) {
  var locales = {
    de: "dddd, MMMM , YYYY"
  };

  if (locales[locale] === undefined) {
    locale = "de";
  }

  moment.locale(locale);
  moment.updateLocale(locale, {
    longDateFormat: {
      LLLL: locales[locale]
    }
  });

  return locale;
}

LoadLocale.listen((locale) => {
  let normalizedLocale = locale.substr(0, 2);
  let activeLocale = setLocale(normalizedLocale);

  LoadLocale.completed(MessageStrings[activeLocale]);
});

export default LoadLocale;
