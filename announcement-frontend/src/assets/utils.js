
const TIMEZONE = Intl.DateTimeFormat().resolvedOptions().timeZone
const formatInteger = (time, places) => time.toString().padStart(places, "0")

function getDate(dateStr) {
    const timeZoneDate = new Date(new Date(dateStr).toLocaleString('en-US', { timeZone: TIMEZONE }))
    const day = timeZoneDate.toLocaleString('default', { day: 'numeric' })
    const month = timeZoneDate.toLocaleString('default', { month: 'short' })
    const year = timeZoneDate.toLocaleString('default', { year: 'numeric' })
    const hour = formatInteger(timeZoneDate.getHours(), 2)
    const minute = formatInteger(timeZoneDate.getMinutes(), 2)
  
    return `${day} ${month} ${year}, ${hour}:${minute}`
  
}

const formatDateString = (date) => {
    if (date) {
        date = new Date(date)
        return `${date.getFullYear()}-${formatInteger(date.getMonth()+1,2)}-${formatInteger(date.getDate(),2)} ${formatInteger(date.getHours(),2)}:${formatInteger(date.getMinutes(),2)}`
    } else { return null }
} // YYYY-MM-DD HH:MM:SS
const formatDate = (date) => date ? getDate(date) : '-'
const getCurrentDate = () => {
    const date = new Date() 
    return`${date.getFullYear()}-${formatInteger(date.getMonth()+1,2)}-${formatInteger(date.getDate(),2)}`
}
const getDateTime = (date,time) => {
    const utc = date && time ? `${date} ${time}`.trim() : null
    return utc ? new Date(utc) : null
}
export { formatDate,formatDateString,TIMEZONE,getCurrentDate,getDateTime }