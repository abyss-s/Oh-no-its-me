export const ICONS = {
  ARROW: '/assets/icons/arrow-right.svg',
  CHECK: '/assets/icons/check.svg',
  SPINNER: 'assets/icons/spinnergif.gif',
  TOAST_ICON: {
    success: '/assets/icons/success.svg',
    warning: '/assets/icons/warning.svg',
    information: '/assets/icons/info.svg',
  },
  HOME: '/assets/icons/home.png',
  LOGOUT: '/assets/icons/logout.png',
  QUESTION_ICON: (icon: string) => `/assets/images/question/${encodeURIComponent(icon)}.svg`,
};
