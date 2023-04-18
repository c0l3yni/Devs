export const getLogFile = async (path) => {
  const file = await fetch(path);
  return await file.text();
};

export const logFileToArray = (fileText) => {
  return fileText.split("\r\n");
};

export function getFormattedDate(date) {
  const day = date.getDate() < 10 ? `0${date.getDate()}` : date.getDate();
  const month =
    date.getMonth() + 1 < 10 ? `0${date.getMonth() + 1}` : date.getMonth() + 1;
  const year = date.getFullYear();
  return `${year}-${month}-${day}`;
}

export function getDateRange(dateString) {
  let lastDate = dateString ? new Date(dateString) : new Date();
  const startDate = new Date();
  if (dateString) {
    lastDate.setDate(lastDate.getUTCDate());
    startDate.setMonth(lastDate.getMonth());
    startDate.setFullYear(lastDate.getFullYear());
    startDate.setDate(lastDate.getUTCDate() - 29);
  } else {
    startDate.setDate(lastDate.getUTCDate() - 28);
  }
  return { lastDate, startDate };
}

export const createMapWithLast28Days = (dateString) => {
  const dateMap = new Map();
  let { lastDate, startDate } = getDateRange(dateString);
  while (startDate.toDateString() !== lastDate.toDateString()) {
    startDate.setDate(startDate.getDate() + 1);
    const formattedDate = getFormattedDate(startDate);
    dateMap.set(formattedDate, 0);
  }
  return dateMap;
};

export const populateMapWithFailures = (dateMap, failureList) => {
  failureList.forEach((failureDate) => {
    if (dateMap.has(failureDate)) {
      const currentFailureCount = dateMap.get(failureDate);
      dateMap.set(failureDate, currentFailureCount + 1);
    }
  });
  return dateMap;
};
