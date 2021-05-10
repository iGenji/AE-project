const STORE_NAME = "user";


const getUserSessionData = () => {
  const retrievedUser = sessionStorage.getItem(STORE_NAME);
  if (!retrievedUser) return;
  return JSON.parse(retrievedUser);
};

const setUserSessionData = (user) => {
  const storageValue = JSON.stringify(user);
  sessionStorage.setItem(STORE_NAME, storageValue);
};

const removeSessionData = () => {
  sessionStorage.removeItem(STORE_NAME);
};

export {
  getUserSessionData,
  setUserSessionData,
  removeSessionData,
};
