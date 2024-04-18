import seaborn as sns
import matplotlib.pyplot as plt
import pandas as pd
import pypyodbc as odbc
import os
import sys
import logging

sql_statement = sys.argv[1]
graph_choice = int(sys.argv[2])
graph_name = sys.argv[3]
graph_x_name = sys.argv[4]
graph_y_name = sys.argv[5]
graph_y2_name = sys.argv[6]

# # Configuration for Logging File. Returns any errors with execution
logging.basicConfig(filename='graphErrorLog.log', level=logging.ERROR,
                    format='%(asctime)s - %(levelname)s - %(message)s\n')


def getDataFrame(query):
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


def graphPathDirectory():
    """
    Gets the path for the graph working directory.

    Returns:
    str: The path to the directory where the generated graphs are stored."""

    path = os.path.dirname(__file__)
    lst = path.split("\x5c")
    lst.pop()
    graph_path = "\x5c".join(lst) + "\x5c" + "generated_graphs" + "\x5c"
    return graph_path


def make_bar(df, name, height, width, x_name, y_name):
    """
    Generates a bar graph based on the provided DataFrame and saves it as an image file.

    Args:
        :param df: The DataFrame containing the data to be plotted.
        :param name: The name of the graph and the output image file.
        :param height: The height of the generated figure.
        :param width: The width of the generated figure.
        :param x_name: The label for the x-axis.
        :param y_name: The label for the y-axis.

    Returns:
        None

    Note:
        This function requires the seaborn and matplotlib libraries to be installed.
    """
    try:
        plt.figure(figsize=(width, height))
        sns.set_style("dark")
        sns.barplot(x=x_name, y=y_name, data=df, palette="flare")
        plt.xlabel(x_name)
        plt.ylabel(y_name)
        plt.title(name)
        plt.savefig(graphPathDirectory() + name + ".png")
    except Exception as e:
        raise RuntimeError("An error occurred while making bar graph: %s" % e)


def make_scatter(df, name, height, width, x_name, y_name):
    """
    Generates a scatter plot based on the provided DataFrame and saves it as an image file.

    Args:
        :param df: The DataFrame containing the data to be plotted.
        :param name: The name of the graph and the output image file.
        :param height: The height of the generated figure.
        :param width: The width of the generated figure.
        :param x_name: The label for the x-axis.
        :param y_name: The label for the y-axis.

    Returns:
        None

    Note:
        This function requires the seaborn and matplotlib libraries to be installed.
    """
    try:
        plt.figure(figsize=(width, height))
        sns.set_style("white")
        sns.scatterplot(x=x_name, y=y_name, data=df)
        plt.xlabel(x_name)
        plt.ylabel(y_name)
        plt.title(name)
        plt.savefig(graphPathDirectory() + name + ".png", dpi=100)
    except Exception as e:
        raise RuntimeError("An error occurred while making scatter plot: %s" % e)


def make_line(df, name, height, width, x_name, y_name):
    """
    Generates a line graph based on the provided DataFrame and saves it as an image file.

    Args:
        :param df: The DataFrame containing the data to be plotted.
        :param name: The name of the graph and the output image file.
        :param height: The height of the generated figure.
        :param width: The width of the generated figure.
        :param x_name: The label for the x-axis.
        :param y_name: The label for the y-axis.

    Returns:
        None

    Note:
        This function requires the seaborn and matplotlib libraries to be installed.
    """
    try:
        plt.figure(figsize=(width, height))
        sns.set_palette("flare")
        sns.set_style("dark")
        sns.lineplot(x=x_name, y=y_name, data=df)
        plt.xlabel(x_name)
        plt.ylabel(y_name)
        plt.title(name)
        plt.savefig(graphPathDirectory() + name + ".png", dpi=70)
    except Exception as e:
        raise RuntimeError("An error occurred while making line graph: %s" % e)


def make_double_line(df, name, height, width, x_name, y_name, y2_name):
    """
    Generates a double line graph based on the provided DataFrame and saves it as an image file.

    Args:
        :param df: The DataFrame containing the data to be plotted.
        :param name: The name of the graph and the output image file.
        :param height: The height of the generated figure.
        :param width: The width of the generated figure.
        :param x_name: The label for the x-axis.
        :param y1_name: The label for the y-axis of the first line.
        :param y2_name: The label for the y-axis of the second line.

    Returns:
        None

    Note:
        This function requires the seaborn and matplotlib libraries to be installed.
    """
    try:
        plt.figure(figsize=(width, height))
        sns.set_palette("flare")
        sns.set_style("dark")
        sns.lineplot(x=x_name, y=y_name, data=df, label=y_name)
        sns.lineplot(x=x_name, y=y2_name, data=df, label=y2_name)
        plt.xlabel(x_name)
        plt.ylabel("Values")
        plt.title(name)
        plt.legend()
        plt.savefig(graphPathDirectory() + name + ".png", dpi=100)
    except Exception as e:
        raise RuntimeError("An error occurred while making double line graph: %s" % e)


def make_pie_chart(df, name, height, width, x_name):
    """
    Generates a scatter plot based on the provided DataFrame and saves it as an image file.

    Args:
        :param df: The DataFrame containing the data to be plotted.
        :param name: The name of the graph and the output image file.
        :param height: The height of the generated figure.
        :param width: The width of the generated figure.
        :param x_name: The label and column to select in the dataframe for the pie chart.

    Returns:
        None

    Note:
        This function requires the seaborn and matplotlib libraries to be installed.
    """

    try:
        plt.figure(figsize=(width, height))
        sns.set_style("darkgrid")
        data = df[x_name].value_counts()
        colors = sns.color_palette("flare", len(data))

        fig1, ax = plt.subplots()
        patches, texts, autotexts = ax.pie(data, labels=data.index, colors=colors, autopct='%1.1f%%', startangle=90,
                                           pctdistance=0.85, shadow=True, wedgeprops={'edgecolor': 'black'})

        # adjust position of percentage labels
        for autotext in autotexts:
            autotext.set_size(11)  # font size for precentages

        plt.title(name)
        plt.savefig(graphPathDirectory() + name + ".png", dpi=80)
    except Exception as e:
        raise RuntimeError("An error occurred while making pie chart: %s" % e)



if __name__ == '__main__':
    try:
        if graph_choice == 1:
            make_scatter(getDataFrame(str(sql_statement)), str(graph_name), 10, 20, graph_x_name, graph_y_name)

        elif graph_choice == 2:
            make_bar(getDataFrame(str(sql_statement)), str(graph_name), 6, 6, graph_x_name, graph_y_name)

        elif graph_choice == 3:
            make_line(getDataFrame(str(sql_statement)), str(graph_name), 7, 14, graph_x_name, graph_y_name)

        elif graph_choice == 4 and graph_y_name == "*" and graph_y2_name == "*":
            make_pie_chart(getDataFrame(str(sql_statement)), str(graph_name), 6, 12, graph_x_name)

        elif graph_choice == 5:
            make_double_line(getDataFrame(str(sql_statement)), str(graph_name), 7, 14, graph_x_name, graph_y_name, graph_y2_name)
    except Exception as e:
        logging.error("An error occurred: %s", e, exc_info=True)
