export const getUserDevice = () => {
  const userAgent = navigator.userAgent;

  if (/Mobi/i.test(userAgent)) {
    return "Mobile";
  } else if (/iPad|iPhone|iPod/i.test(userAgent)) {
    return "iOS";
  } else if (/Android/i.test(userAgent)) {
    return "Android";
  } else if (/Windows Phone/i.test(userAgent)) {
    return "Windows Phone";
  } else if (/Windows|Macintosh|MacIntel|Linux/i.test(userAgent)) {
    return "Desktop";
  } else {
    return "Unknown";
  }
};
