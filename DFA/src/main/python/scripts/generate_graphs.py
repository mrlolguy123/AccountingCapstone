import seaborn as sns
import matplotlib.pyplot as plt
import pandas as pd
import mysql.connector  # deprecated import
import pypyodbc as odbc


# deprecated local database connector
def get_dataframe():
    conn = mysql.connector.connect(
        host="localhost",
        user="root",
        password="",
        database="test"
    )

    query = "SELECT * FROM `mpg` ORDER BY `displacement` ASC, `horsepower` ASC;"
    df = pd.read_sql_query(query, conn)

    conn.close()
    return df


def getDataFrame(query):
    # will leave creds in python file for now, implement security later
    username = "capstoneAdmin"
    password = "Group7@Capstone"

    server = "dfaserver.database.windows.net"
    database = "DFADatabase"
    connection_string = "Driver={ODBC Driver 18 for SQL Server};Server=tcp:" + server + ",1433;Database=" + database + ";Uid=" + username + ";Pwd=" + password + ";Encrypt=yes;TrustServerCertificate=no;Connection Timeout=30;"

    conn = odbc.connect(connection_string)

    cursor = conn.cursor()
    cursor.execute(query)

    dataset = cursor.fetchall()
    columns = [column[0] for column in cursor.description]
    dataframe = pd.DataFrame(dataset, columns=columns)

    return dataframe


def make_bar(df, name, height, width, x_name, y_name):
    plt.figure(figsize=(width, height))
    # sns.color_palette(palette)
    sns.set_style("dark")
    sns.barplot(x=x_name, y=y_name, data=df)
    plt.xlabel(x_name)
    plt.ylabel(y_name)
    plt.title(name)
    plt.savefig(
        "C:/Users/Aman Sahu/Desktop/Capstone 2024/AccountingCapstone/DFA/src/main/python/generated_graphs/sample_" + name + ".png")


def make_scatter(df, name, height, width, x_name, y_name):
    plt.figure(figsize=(width, height))
    sns.set_style("white")
    sns.scatterplot(x=x_name, y=y_name, data=df)
    plt.xlabel(x_name)
    plt.ylabel(y_name)
    plt.title(name)
    plt.savefig("C:/Users/Aman Sahu/Desktop/Capstone 2024/AccountingCapstone/DFA/src/main/python/generated_graphs/sample_" + name + ".png")


def make_boxplot(df, name, height, width, x_name, y_name):
    plt.figure(figsize=(width, height))
    sns.set_style("dark")
    sns.boxplot(x=x_name, y=y_name, data=df)
    plt.xlabel(x_name)
    plt.ylabel(y_name)
    plt.xticks(rotation=90)
    plt.title(name)
    plt.savefig(
        "C:/Users/Aman Sahu/Desktop/Capstone 2024/AccountingCapstone/DFA/src/main/python/generated_graphs/sample_" + name + ".png",
        dpi=100)


if __name__ == '__main__':
    make_bar(getDataFrame("SELECT * FROM amanFinancialRecords ORDER BY totalprofit ASC"), "BarGraph", 20, 7, 'year', 'totalprofit')
    make_scatter(getDataFrame("SELECT * FROM amanFinancialRecords ORDER BY totalprofit ASC"), "ScatterPlot", 10, 5, 'year', 'totalprofit')
    # make_boxplot(get_dataframe(), "BoxPlot", 7, 30, 'horsepower', 'mpg')
    # make_catplot(get_dataframe(), "Cat Plot", 7, 30, 'horsepower', 'mpg')
